package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrateHandler extends UserRequestHandler {
    private static final String command = "/signup";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        if (request.getUserSession().getState().equals(States.CONVERSATION_STARTED)) {
            UserSession session = request.getUserSession();
            session.setState(States.WAITING_FOR_MAIL);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "Send your mail⤵️");
            return;
        }
        if (request.getUserSession().getState().equals(States.WAITING_FOR_MAIL)) {
            UserSession session = request.getUserSession();
            session.setEmail(request.getUpdate().getMessage().getText());
            session.setState(States.WAITING_FOR_PASSWORD);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "Send your password (8-16 digits)⤵️");
            return;
        }
        if (request.getUserSession().getState().equals(States.WAITING_FOR_PASSWORD)) {
            UserSession session = request.getUserSession();
            session.setPassword(request.getUpdate().getMessage().getText());
            session.setState(States.SUCCESSFULLY_SIGNED_UP);
            sessionService.saveSession(request.getChatId(), session);
            Users user = Users.builder()
                    .verified(false)
                    .email(session.getEmail())
                    .password(session.getPassword())
                    .profileName(request.getUpdate().getMessage().getChat().getFirstName())
                    .build();

            registryService.signUp(user);
            telegramService.sendMessage(request.getChatId(), "You're signed up in our system. Now you need to activate your mail by sending me an activation code, which we have sent on your email ⤵");

        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
