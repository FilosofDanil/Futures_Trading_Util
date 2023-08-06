package com.example.telegram_api.components.impl.texthandlers.registration;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingPasswordHandler implements TextHandler {
    private final States applicable = States.WAITING_FOR_PASSWORD;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        session.setPassword(request.getUpdate().getMessage().getText());
        Users user = Users.builder()
                .verified(false)
                .email(session.getEmail())
                .password(session.getPassword())
                .profileName(request.getUpdate().getMessage().getChat().getUserName())
                .build();
        registryService.signUp(user);
        sessionService.saveSession(request.getChatId(), session);
        session.setState(States.SUCCESSFULLY_SIGNED_UP);
        telegramService.sendMessage(request.getChatId(), "You're signed up in our system. Now you need to activate your mail by sending me an activation code, which we have sent on your email ⤵");
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
