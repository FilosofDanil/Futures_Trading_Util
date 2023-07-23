package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrateTextHandler extends UserRequestHandler {
    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }

    @Override
    public void handle(UserRequest request) {
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
                    .profileName(request.getUpdate().getMessage().getChat().getUserName())
                    .build();

            registryService.signUp(user);
            telegramService.sendMessage(request.getChatId(), "You're signed up in our system. Now you need to activate your mail by sending me an activation code, which we have sent on your email ⤵");
            return;
        }
        if (request.getUserSession().getState().equals(States.SUCCESSFULLY_SIGNED_UP)) {
            UserSession session = request.getUserSession();
            session.setState(States.ACTIVATED);
            sessionService.saveSession(request.getChatId(), session);
            UsernameModel usernameModel = new UsernameModel(request.getUpdate().getMessage().getChat().getUserName());
            registryService.activate(request.getUpdate().getMessage().getText(), usernameModel);
            telegramService.sendMessage(request.getChatId(), "Your account has been activated, and you could authorize by just clicking /login and enjoy our service.");

        }
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
