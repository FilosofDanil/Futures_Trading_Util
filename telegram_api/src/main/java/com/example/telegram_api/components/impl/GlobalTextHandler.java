package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ResponseParser;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GlobalTextHandler extends UserRequestHandler {

    private final List<TextHandler> textHandlers;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }


    @Override
    public void handle(UserRequest request) {
        try {
            UserSession session = request.getUserSession();
            if(!session.getAuth()  && session.getState()!= States.LOGIN_WAIT_PASSWORD){
                telegramService.sendMessage(request.getChatId(), "You're need to login(1-2 mins)");
                return;
            }
            for (TextHandler textHandler : textHandlers) {
                if(textHandler.getApplicableState().equals(session.getState())){
                    textHandler.handle(request);
                    return;
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
