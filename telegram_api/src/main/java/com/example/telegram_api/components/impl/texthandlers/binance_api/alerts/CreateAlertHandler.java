package com.example.telegram_api.components.impl.texthandlers.binance_api.alerts;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.functional.PriceService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class CreateAlertHandler implements TextHandler {
    private final States applicable = States.WAITING_FOR_PRICE;

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    private final AlertsService alertsService;

    private final PriceService priceService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = sessionService.getSession(request.getChatId());
        session.setPrice(Double.parseDouble(request.getUpdate().getMessage().getText()));
        Double currentPrice = Double.parseDouble(priceService.getPrice(session.getTicker()));
        Alerts alert = Alerts.builder()
                .alert_date(new Date())
                .current_price(currentPrice)
                .price(session.getPrice())
                .crossed(false)
                .ticker(session.getTicker())
                .user(Users.builder()
                        .profileName(request.getUpdate().getMessage().getChat().getUserName())
                        .build())

                .build();
        Alerts alerts = alertsService.create(alert);
        telegramService.sendMessage(request.getChatId(), "Successfully placed alert " + alerts.toString());
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
