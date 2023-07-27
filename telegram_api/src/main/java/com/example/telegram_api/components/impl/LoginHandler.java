package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginHandler extends UserRequestHandler {
    private static final String command = "/login";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        if (request.getUserSession().getState() != null) {
            UserSession session = request.getUserSession();
            if (session.getAuth()) {
                session.setState(States.SUCCESSFULLY_LOGIN);
                sessionService.saveSession(request.getChatId(), session);
                telegramService.sendMessage(request.getChatId(), "You're already authorized in the system");
                return;
            }
            if (!session.getAuth()) {
                session.setState(States.LOGIN_WAIT_PASSWORD);
                sessionService.saveSession(request.getChatId(), session);
                telegramService.sendMessage(request.getChatId(), "Now write down your password. ");
            }
        } else {
            telegramService.sendMessage(request.getChatId(), "You need to start bot -> /start");
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
