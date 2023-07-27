package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.LoginRequest;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ResponseParser;
import feign.FeignException;
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
        System.out.println("fefrer");
        try {
            UserSession session = request.getUserSession();
            if (!session.getAuth() && session.getState().equals(States.LOGIN_WAIT_PASSWORD)) {
                LoginRequest loginRequest = LoginRequest.builder()
                        .name(request.getUpdate().getMessage().getChat().getUserName())
                        .password(request.getUpdate().getMessage().getText())
                        .build();
                System.out.println(loginRequest);
                if (registryService.login(loginRequest)) {
                    session.setState(States.SUCCESSFULLY_LOGIN);
                    session.setAuth(true);
                    sessionService.saveSession(request.getChatId(), session);
                    telegramService.sendMessage(request.getChatId(), "You've been successfully login, now you can use all bot functions!");
                } else {
                    telegramService.sendMessage(request.getChatId(), "Fail Authorization. Wrong password or username, please try again!");
                }

            }
        } catch (FeignException ex) {
            if (ex.status() == 400) {
                UserSession session = request.getUserSession();
                String message = ResponseParser.extractMessage(ex.getMessage());
                session.setState(States.ERROR);
                sessionService.saveSession(request.getChatId(), session);
                telegramService.sendMessage(request.getChatId(), message + " Please try one more or reload bot with -> /start");
            }
        }
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
