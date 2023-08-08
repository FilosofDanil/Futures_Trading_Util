package com.example.telegram_api.components.impl.texthandlers.binance_api.alerts;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.functional.PriceService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceAlertHandler implements TextHandler {
    private final States applicable = States.PLACE_ALERT;

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    private final PriceService priceService;


    @Override
    public void handle(UserRequest request) {
        try {
            UserSession session = sessionService.getSession(request.getChatId());
            String ticker = request.getUpdate().getMessage().getText();
            String price = priceService.getPrice(ticker);
            session.setState(States.WAITING_FOR_PRICE);
            session.setTicker(ticker);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "Now send price lvl, on which you would be alerted " + "\n" + "Now price is: " + price);
        } catch (FeignException ex) {
            telegramService.sendMessage(request.getChatId(), "Not a ticker! Please try again!");
        }
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
