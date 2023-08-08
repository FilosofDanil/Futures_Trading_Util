package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.functional.ClearService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ReplyKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClearCrossedHandler implements QueryHandler {
    private final static String query = "\uD83D\uDDD1 Clear crossed";

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    private final ClearService clearService;

    private final AlertsService alertsService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        clearService.clear(request.getUpdate().getCallbackQuery().getMessage().getChat().getUserName());
        session.setState(States.CLEARED_ALERTS);
        sessionService.saveSession(request.getChatId(), session);
        List<String> rows = List.of("\uD83D\uDD19 Back");
        ReplyKeyboardMarkup replyKeyboard = ReplyKeyboardHelper.buildMainMenu(rows);
        telegramService.sendMessage(request.getChatId(), "Successfully cleared, click the button below to go back to your list! ", replyKeyboard);

    }

    @Override
    public String getCallbackQuery() {
        return query;
    }
}
