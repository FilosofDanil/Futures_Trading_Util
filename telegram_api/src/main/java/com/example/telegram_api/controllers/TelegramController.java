package com.example.telegram_api.controllers;

import com.example.telegram_api.configs.BotConfig;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.dispatcher.IDispatcher;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.UserService;
import lombok.RequiredArgsConstructor;
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

    private final SessionService sessionService;

    private final UserService userService;

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
        Long chatId;
        String username;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            username = update.getCallbackQuery().getMessage().getChat().getUserName();
        } else {
            chatId = update.getMessage().getChatId();
            username = update.getMessage().getChat().getUserName();
        }

        UserSession session = sessionService.getSession(chatId);
        UserRequest userRequest = UserRequest
                .builder()
                .update(update)
                .chatId(chatId)
                .userSession(session)
                .build();
        userService.saveUser(username, chatId);
        System.out.println(session);
        dispatcher.dispatch(userRequest);
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
