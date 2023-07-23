package com.example.telegram_api.controllers;

import com.example.telegram_api.configs.BotConfig;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.dispatcher.IDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramController extends TelegramLongPollingBot {

    private final IDispatcher dispatcher;

    private final BotConfig config;

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var originalMessage = update.getMessage();
        System.out.println(originalMessage.getText());
        Long chatId = update.getMessage().getChatId();
        UserRequest userRequest = UserRequest
                .builder()
                .update(update)
                .chatId(chatId)
                .build();

        dispatcher.dispatch(userRequest);
//
//        var response = new SendMessage();
//        response.setChatId(originalMessage.getChatId().toString());
//        response.setText("Санчізес");
//        sendMessage(response);
    }

    public void sendMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
