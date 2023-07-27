package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.LoginRequest;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginTextHandler extends UserRequestHandler {

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        if (!request.getLogined() && session.getState().equals(States.LOGIN_WAIT_PASSWORD)) {
            LoginRequest loginRequest = LoginRequest.builder()
                    .email(request.getUserSession().getEmail())
                    .name(request.getUpdate().getMessage().getChat().getUserName())
                    .password(request.getUserSession().getPassword())
                    .build();
            if(registryService.login(loginRequest))
            session.setState(States.SUCCESSFULLY_LOGIN);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "You've been successfully login, now you can use all bot functions!");
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
