package com.khnu.yakymchuk.telegramBot;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.dagger.DaggerIApplicationComponent;
import com.khnu.yakymchuk.enums.Role;
import com.khnu.yakymchuk.exception.CommandNotFoundException;
import com.khnu.yakymchuk.exception.IExceptionHandler;
import com.khnu.yakymchuk.exception.RequestBuilderNotFoundException;
import com.khnu.yakymchuk.exception.UserNotFoundException;
import com.khnu.yakymchuk.exception.UserReadableException;
import com.khnu.yakymchuk.model.User;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.ITokenService;
import com.khnu.yakymchuk.service.IUserService;
import com.khnu.yakymchuk.utils.BotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.inject.Inject;
import java.util.Map;

import static com.khnu.yakymchuk.enums.Role.ADMIN;
import static com.khnu.yakymchuk.enums.Role.WAITER;

public class RKeeperBot extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(RKeeperBot.class);

    private ITokenService tokenService;
    private IUserService userService;
    private IExceptionHandler exceptionHandler;
    private Map<String, IActionCommand> commandMap;
    private Map<String, IRequestBuilder> requestBuilderMap;

    public RKeeperBot() {
        DaggerIApplicationComponent.create().inject(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        LOG.info("Method onUpdateReceived started");
        try {
            Message message = update.getMessage();
            boolean hasCallbackQuery = update.hasCallbackQuery();

            if (!hasCallbackQuery && message.getText().equals("/start")) {
                authorizeNewUser(update);
            } else if (!hasCallbackQuery && update.getMessage() != null) {
                logInNewWaiter(update);
            } else {
                Role role = isAdmin(update) ? ADMIN : WAITER;
                LOG.debug("User authorized with role :  {}", role);
                processUserCommands(update, role);
            }
        } catch (TelegramApiException e) {
            LOG.info(e.getMessage());
            exceptionHandler.handleThrowable(e);
        }
    }

    private boolean isAdmin(Update update) {
        String telegramBotId = update.getCallbackQuery().getMessage().getChatId().toString();
        User user = userService.getUserByTelegramBotId(telegramBotId);
        if (user != null) {
            return ADMIN == Role.fromValue(user.getRole());
        }
        return false;
    }

    private void authorizeNewUser(Update update) throws TelegramApiException {
        SendMessage sendMessage = null;
        String userId = String.valueOf(update.getMessage().getFrom().getId());
        User user = userService.getUserByTelegramBotId(userId);
        if (user != null) {
            Role role = Role.fromValue(user.getRole());
            if (ADMIN == role || WAITER == role) {
                sendMessage = new SendMessage(update.getMessage().getChatId(), "You are authorized as " + user.getName() + " , choose the command");
                sendMessage.setReplyMarkup(BotUtils.buildMainKeyboard(role));
            }
        } else {
            sendMessage = new SendMessage(update.getMessage().getChatId(), "Type the the authorization token");
        }
        assert sendMessage != null;
        execute(sendMessage);
    }

    private void logInNewWaiter(Update update) {
        try {
            SendMessage sendMessage;
            User user = checkUser(update);
            userService.addUser(user);
            String authorizationMessage = user.getLastName() == null ? "You are authorized as " + user.getName() : "You are authorized as " + user.getName() + " " + user.getLastName();

            sendMessage = new SendMessage(update.getMessage().getChatId(), authorizationMessage);
            sendMessage.setReplyMarkup(BotUtils.buildMainMenuInlineKeyboardForWaiter());
            execute(sendMessage);
        } catch (UserNotFoundException e) {
            exceptionHandler.handleThrowable(e);
        } catch (TelegramApiException e) {
            LOG.error(e.getMessage());
        }
    }

    private User checkUser(Update update) {
        User user;
        String tokenName = update.getMessage().getText();
        if (tokenService.getTokenAndDelete(tokenName) != null) {
            String name = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String userId = String.valueOf(update.getMessage().getFrom().getId());
            user = new User(name, lastName, userId, "Waiter");
            return user;
        }
        throw new UserNotFoundException("There is no such token!");
    }

    /**
     * Processing user's commands logic (probably will be located in another class)
     */
    private void processUserCommands(Update update, Role role) {
        EditMessageText editMessageText = null;

        CallbackQuery callbackQuery = update.getCallbackQuery();
        Message callBackMessage = update.getCallbackQuery().getMessage();
        String callBackData = update.getCallbackQuery().getData();
        LOG.info("Callback data: {}", callBackData);

        if (update.hasCallbackQuery()) {
            SendMessage sendMessage;
            int messId = callbackQuery.getMessage().getMessageId();
            long chatId = callbackQuery.getMessage().getChatId();

            if ("Cancel".equalsIgnoreCase(callBackData)) {
                sendMessage = new SendMessage(update.getCallbackQuery().getMessage().getChatId(), "You canceled the command");
                /*
                  Build main menu keyboard depends on current role;
                 */
                InlineKeyboardMarkup keyboardMarkup = BotUtils.buildMainKeyboard(role);
                editMessageText = BotUtils.hideLastMessage(chatId, messId, keyboardMarkup, sendMessage.getText());
            } else {
                IRequestBuilder requestBuilder = getRequestBuilderByInputParams(update.getCallbackQuery().getData());
                LOG.debug("Request builder : {}", requestBuilder);
                IActionCommand command = getCommandByInputParams(callBackData);

                String commandName = command.getClass().getSimpleName();

                if (callBackData.endsWith("Finish") || (commandName.startsWith("Show"))) {
                    IRequest request = requestBuilder.getRequest(callBackData);
                    sendMessage = new SendMessage(callbackQuery.getMessage().getChatId(), command.execute(request));
                    InlineKeyboardMarkup keyboardMarkup = BotUtils.buildMainKeyboard(role);
                    editMessageText = BotUtils.hideLastMessage(chatId, messId, keyboardMarkup,
                            sendMessage.getText());
                } else {
                    sendMessage = new SendMessage(callBackMessage.getChatId(), requestBuilder.getInstructionsToUser(update));
                    InlineKeyboardMarkup keyboardMarkup = BotUtils.buildInlineKeyboard(sendMessage,
                            requestBuilder.getMenuParameters(callBackData), callBackData);
                    editMessageText = BotUtils.hideLastMessage(chatId, messId, keyboardMarkup,
                            requestBuilder.getInstructionsToUser(update));
                }
            }
        }
        try {
            assert editMessageText != null;
            execute(editMessageText);
        } catch (TelegramApiException e) {
            LOG.error(e.getMessage());
        }
    }

    @Inject
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Inject
    public void setTokenService(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    private IRequestBuilder getRequestBuilderByInputParams(String params) {
        String builder = BotUtils.getAllCommandsName().get(params.split(" ")[0]);
        if (builder == null) {
            throw new RequestBuilderNotFoundException("Cannot find " + params + " request builder");
        } else {
            return defineRequestBuilder(builder);
        }
    }

    private IActionCommand getCommandByInputParams(String params) {
        String command = BotUtils.getAllCommandsName().get(params.split(" ")[0]);
        if (command == null) {
            throw new CommandNotFoundException("Cannot find " + params + " command");
        } else {
            return defineCommand(command);
        }
    }

    public void sendErrorMessageToUser(long chatId, UserReadableException e) {
        LOG.info("Trying to sent error message to user..");

        String errorMessage = e.getMessage();
        SendMessage sendMessage = new SendMessage(chatId, errorMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e1) {
            LOG.error(e1.getMessage());
        }
    }

    private String getParamFromSSM(String paramName) {
        AWSSimpleSystemsManagement client = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        GetParameterRequest getParametersRequest = new GetParameterRequest();
        getParametersRequest.setWithDecryption(true);
        return client.getParameter(getParametersRequest.withName(paramName)).getParameter().getValue();
    }

    @Override
    public String getBotUsername() {
        return getParamFromSSM("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return getParamFromSSM("BOT_ID");
    }

    @Inject
    void setCommandMap(Map<String, IActionCommand> commandMap) {
        this.commandMap = commandMap;
    }

    @Inject
    void setRequestBuilderMap(Map<String, IRequestBuilder> requestBuilderMap) {
        this.requestBuilderMap = requestBuilderMap;
    }

    @Inject
    void setExceptionHandler(IExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    private IActionCommand defineCommand(String command) {
        if (commandMap.containsKey(command)) {
            return commandMap.get(command);
        } else {
            throw new CommandNotFoundException("Incorrect parameters - '" + command + "'");
        }
    }

    private IRequestBuilder defineRequestBuilder(String request) {
        if (requestBuilderMap.containsKey(request)) {
            return requestBuilderMap.get(request);
        } else {
            throw new RequestBuilderNotFoundException("Incorrect parameters - '" + request + " '");
        }
    }
}

