package com.example.telegram_api.components.impl.texthandlers;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ResponseParser;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriceTextHandler extends UserRequestHandler {
    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }

    @Override
    public void handle(UserRequest request) {
       try{
           UserSession session = request.getUserSession();
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
