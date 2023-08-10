package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.InlineKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AlertsManageIntHandler implements QueryHandler {
    private static final String query = "";

    private final AlertsService alertsService;

    private final TelegramBotService telegramService;


    @Override
    public void handle(UserRequest request) {
        Long deleteId = Long.parseLong(request.getUpdate().getCallbackQuery().getData());
        alertsService.delete(deleteId);
        List<Alerts> alerts = alertsService.getAll(request.getUpdate().getCallbackQuery().getMessage().getChat().getUserName());
        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardHelper.buildInlineKeyboard(responseListFormer(alerts), false, defineMap(alerts));
        telegramService.sendMessage(request.getChatId(), "Successfully deleted! "+"\uD83D\uDC68\u200D\uD83D\uDE80 Alerts list: \n", keyboardMarkup);
    }

    private List<String> responseListFormer(List<Alerts> alertsList) {
        List<String> responseStringList = new ArrayList<>();
        alertsList.forEach(alert -> {
            responseStringList.add("\uD83D\uDDD1 " + alert.getTicker() + " price level: " + alert.getPrice());
        });
        responseStringList.add("\uD83D\uDD19 Back");
        return responseStringList;
    }

    private Map<String, String> defineMap(List<Alerts> alertsList) {
        Map<String, String> map = new HashMap<>();
        alertsList.forEach(alert -> {
            map.put("\uD83D\uDDD1 " + alert.getTicker() + " price level: " + alert.getPrice(), alert.getId().toString());
        });
        map.put("\uD83D\uDD19 Back", "\uD83D\uDD19 Back");
        return map;
    }

    @Override
    public String getCallbackQuery() {
        return query;
    }

    @Override
    public boolean isInteger() {
        return true;
    }
}
