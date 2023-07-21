package com.example.telegram_api.components.impl;

import com.example.telegram_api.client.DataClient;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.services.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StartCommandHandler extends UserRequestHandler {
    private final DataClient client;

    private final TelegramBotService telegramService;

    private static final String command = "/start";

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        List<Users> list = client.getAll();
        telegramService.sendMessage(request.getChatId(),
                list.toString());
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}