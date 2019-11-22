package com.khnu.yakymchuk.utils;

import com.khnu.yakymchuk.enums.Role;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotUtils {

    private static Map<String, Map<String, String>> mainMenuWaiterMap() {
        Map<String, Map<String, String>> mainMenuWaiterMap = new HashMap<>();
        mainMenuWaiterMap.put("Menu", menuMap());
        mainMenuWaiterMap.put("Orders", ordersMap());
        mainMenuWaiterMap.put("Payment", paymentMap());
        mainMenuWaiterMap.put("Tables", tablesMap());
        return mainMenuWaiterMap;
    }

    private static Map<String, String> ordersMap() {
        Map<String, String> commands = new HashMap<>();
        commands.put("do", "Delete order");
        commands.put("sao", "Show active orders");
        commands.put("sdo", "Show daily orders");
        commands.put("mo", "Make order");
        return commands;
    }

    private static Map<String, String> paymentMap() {
        Map<String, String> commands = new HashMap<>();
        commands.put("md", "Make discount");
        commands.put("mp", "Make payment");
        return commands;
    }

    private static Map<String, String> tablesMap() {
        Map<String, String> commands = new HashMap<>();
        commands.put("sat", "Show active tables");
        commands.put("sf", "Show free tables");
        return commands;
    }

    private static Map<String, String> menuMap() {
        Map<String, String> commands = new HashMap<>();
        commands.put("sm", "Show menu");
        return commands;
    }

    private static Map<String, String> getWaiterCommandsName() {
        Map<String, String> commands = new HashMap<>();
        commands.put("sm", "Show menu");
        commands.put("sat", "Show active tables");
        commands.put("sf", "Show free tables");
        commands.put("sao", "Show active orders");
        commands.put("sdo", "Show daily orders");
        commands.put("mo", "Make order");
        commands.put("do", "Delete order");
        commands.put("md", "Make discount");
        commands.put("mp", "Make payment");
        return commands;
    }

    public static Map<String, String> getAllCommandsName() {
        Map<String, String> commands = new HashMap<>();
        commands.put("sm", "Show menu");
        commands.put("sat", "Show active tables");
        commands.put("sf", "Show free tables");
        commands.put("sao", "Show active orders");
        commands.put("sdo", "Show daily orders");
        commands.put("mo", "Make order");
        commands.put("mp", "Make payment");
        commands.put("do", "Delete order");
        commands.put("md", "Make discount");
        commands.put("au", "AddUser");
        commands.put("du", "DeleteUser");
        return commands;
    }

    public static EditMessageText hideLastMessage(long chatId, int messageId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup buildMainKeyboard(Role role) {
        switch (role) {
            case ADMIN: {
                return buildMenuKeyboardForAdmin();
            }
            case WAITER: {
                return buildMainMenuInlineKeyboardForWaiter();
            }
        }
        return null;
    }

    public static InlineKeyboardMarkup defineInnerKeyboard(String key) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        Map<String, String> map = mainMenuWaiterMap().get(key);
        if (map != null) {
            keyboardMarkup = buildKeyboardByInputMap(map);
        }
        return keyboardMarkup;
    }

    /**
     * Build key
     *
     * @param map
     * @return
     */
    private static InlineKeyboardMarkup buildKeyboardByInputMap(Map<String, String> map) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, String> inputMap : map.entrySet()) {
            count++;
            rowInline.add(new InlineKeyboardButton().setText(inputMap.getValue()).setCallbackData(inputMap.getKey()));
            if (count == 2) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
                count = 0;
            }
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

//    /**
//     * Main menu keyboard for admin
//     *
//     * @return
//     */
//    public static InlineKeyboardMarkup buildMainMenuInlineKeyboardForWaiter() {
//        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        int count = 0;
//        for (Map.Entry<String, Map<String, String>> map : mainMenuWaiterMap().entrySet()) {
//            count++;
//            rowInline.add(new InlineKeyboardButton().setText(map.getKey()).setCallbackData(map.getKey()));
//            if (count == 2) {
//                rowsInline.add(rowInline);
//                rowInline = new ArrayList<>();
//                count = 0;
//            }
//        }
//        rowsInline.add(rowInline);
//        markupInline.setKeyboard(rowsInline);
//        return markupInline;
//    }

    public static InlineKeyboardMarkup buildMainMenuInlineKeyboardForWaiter() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, String> map : getWaiterCommandsName().entrySet()) {
            count++;
            rowInline.add(new InlineKeyboardButton().setText(map.getValue()).setCallbackData(map.getKey()));
            if (count == 2) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
                count = 0;
            }
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }


    /**
     * Main keyboard when you are authorized as admin.
     *
     * @return
     */
    private static InlineKeyboardMarkup buildMenuKeyboardForAdmin() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        rows.add(new InlineKeyboardButton().setText("AddUser").setCallbackData("au"));
        rows.add(new InlineKeyboardButton().setText("DeleteUser").setCallbackData("du"));
        keyboardButtons.add(rows);
        markup.setKeyboard(keyboardButtons);
        return markup;
    }

    /**
     * Keyboard that appears while building command params for both types of users.
     *
     * @param message
     * @param buttons
     * @param callBackParam
     * @return
     */
    public static InlineKeyboardMarkup buildInlineKeyboard(SendMessage message, List<String> buttons, String callBackParam) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        int count = 0;
        for (String button : buttons) {
            count++;
            rowInline.add(new InlineKeyboardButton().setText(button).setCallbackData(callBackParam + " " + button));
            if (count == 2) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
                count = 0;
            }
        }
        rowInline.add(new InlineKeyboardButton().setText("Finish").setCallbackData(callBackParam + " Finish"));
        rowInline.add(new InlineKeyboardButton().setText("Cancel").setCallbackData("Cancel"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return markupInline;
    }

}
