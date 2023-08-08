package com.example.telegram_api.components.impl.texthandlers.registration;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrateTextHandler implements TextHandler {
    private final States applicable = States.WAITING_FOR_MAIL;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public void handle(UserRequest request) throws FeignException {
        UserSession session = request.getUserSession();
        session.setEmail(request.getUpdate().getMessage().getText());
        session.setState(States.WAITING_FOR_PASSWORD);
        sessionService.saveSession(request.getChatId(), session);
        telegramService.sendMessage(request.getChatId(), "Send your password (8-16 digits)⤵️");
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
