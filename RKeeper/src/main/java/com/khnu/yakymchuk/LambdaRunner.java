package com.khnu.yakymchuk;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.khnu.yakymchuk.constant.LambdaConstant;
import com.khnu.yakymchuk.exception.UserReadableException;
import com.khnu.yakymchuk.telegramBot.RKeeperBot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@LambdaHandler(lambdaName = LambdaConstant.LAMBDA_NAME, roleName = LambdaConstant.LAMBDA_ROLE)
public class LambdaRunner implements RequestStreamHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LambdaRunner.class);

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Update update = mapper.readValue(inputStream, Update.class);
        RKeeperBot rKeeperBot = new RKeeperBot();
        try {
            rKeeperBot.onUpdateReceived(update);
        } catch (UserReadableException e) {
            LOG.error(e.getMessage());
            rKeeperBot.sendErrorMessageToUser(update.getMessage().getChatId(), e);
        }
    }

}
