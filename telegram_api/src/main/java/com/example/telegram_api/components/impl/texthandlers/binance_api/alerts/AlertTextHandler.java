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
import com.example.telegram_api.sorters.AlertsComparator;
import com.example.telegram_api.util.InlineKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlertTextHandler implements TextHandler {
    private final States applicable = States.ALERT_MENU;

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    private final AlertsService alertsService;

    private final PriceService priceService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = sessionService.getSession(request.getChatId());
        if (request.getUpdate().getMessage().getText().equals("⚠ My alerts")) {
            session.setState(States.ALL_ALERTS);
            List<Alerts> alerts = alertsService.getAll(request.getUpdate().getMessage().getChat().getUserName());
            String responseList = BinanceApiResponseParser.formResponseFromAlertList(alerts, priceService);
            InlineKeyboardMarkup keyboardMarkup = InlineKeyboardHelper.buildInlineKeyboard(List.of("\uD83D\uDDD1 Clear crossed", " \uD83D\uDE80 Manage alerts"));
            telegramService.sendMessage(request.getChatId(), "Your alerts ⤵ \n" + responseList, keyboardMarkup);
        } else if (request.getUpdate().getMessage().getText().equals("➕ Place alert")) {
            session.setState(States.PLACE_ALERT);
            telegramService.sendMessage(request.getChatId(), "Now write down a ticker ⤵ ");
        } else {
            telegramService.sendMessage(request.getChatId(), "Not a command from menu! ");
        }
        sessionService.saveSession(request.getChatId(), session);
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }

    private static class BinanceApiResponseParser {
        private static String formResponseFromAlertList(List<Alerts> alerts, PriceService priceService) {
            alerts.sort(new AlertsComparator());
            StringBuilder stringBuilder = new StringBuilder();
            for (Alerts alert : alerts) {
                if (alert.getCrossed()) {
                    stringBuilder.append("✅ ");
                } else {
                    stringBuilder.append("❌ ");
                }
                stringBuilder.append(alert.getTicker()).append(" ");
                stringBuilder.append("Alert price lvl: ").append(alert.getPrice()).append(" ");
                stringBuilder.append("Current price: ").append(priceService.getPrice(alert.getTicker()));
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
