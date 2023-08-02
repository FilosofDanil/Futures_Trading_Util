package com.example.telegram_api.components.impl.texthandlers;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.PriceService;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ResponseParser;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceTextHandler extends UserRequestHandler {
    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final PriceService priceService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }

    @Override
    public void handle(UserRequest request) {
        try {
            System.out.println("fefrfre");
            UserSession session = request.getUserSession();
            if (session.getState().equals(States.WAITING_FOR_TICKER)) {
                System.out.println("ferferf");
                String ticker = request.getUpdate().getMessage().getText();
                String response = priceService.getPrice(ticker);
                System.out.println(response);
                telegramService.sendMessage(request.getChatId(), "The current price is " + response);
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
