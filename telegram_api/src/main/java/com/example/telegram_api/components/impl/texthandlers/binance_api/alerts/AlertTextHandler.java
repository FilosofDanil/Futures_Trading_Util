package com.example.telegram_api.components.impl.texthandlers.binance_api.alerts;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlertTextHandler implements TextHandler {
    private final States applicable = States.ALERT_MENU;

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    private final AlertsService alertsService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = sessionService.getSession(request.getChatId());
        if (request.getUpdate().getMessage().getText().equals("⚠ My alerts")) {
            session.setState(States.ALL_ALERTS);
            List<Alerts> alerts = alertsService.getAll(request.getUpdate().getMessage().getChat().getUserName());
            System.out.println(alerts);
            telegramService.sendMessage(request.getChatId(), "Your alerts ⤵ " + alerts.toString());
        } else if (request.getUpdate().getMessage().getText().equals("➕ Place alert")) {
            session.setState(States.PLACE_ALERT);
            telegramService.sendMessage(request.getChatId(), "Now write down a ticker ⤵ ");
        } else {
            telegramService.sendMessage(request.getChatId(), "Not a command from menu! ");
        }
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
