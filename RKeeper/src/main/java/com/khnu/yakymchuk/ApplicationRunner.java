package com.khnu.yakymchuk;

import com.khnu.yakymchuk.exception.UserReadableException;
import com.khnu.yakymchuk.telegramBot.RKeeperBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**Probably can be used to test application locally with local dynamo db */
public class ApplicationRunner {

    public static final Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new RKeeperBot());
        } catch (TelegramApiRequestException e) {
            LOG.error(e.getApiResponse());
        } catch (UserReadableException e) {
            LOG.error(e.getMessage());
        }
    }

}
