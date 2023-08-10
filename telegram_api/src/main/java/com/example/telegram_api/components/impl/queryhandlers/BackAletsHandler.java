package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ReplyKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BackAletsHandler implements QueryHandler {

    private static final String query = "\uD83D\uDD19 Back";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = sessionService.getSession(request.getChatId());
        if(session.getState().equals(States.ALERT_MANAGEMENT)){
            session.setState(States.ALERT_MENU);
            sessionService.saveSession(request.getChatId(), session);
            List<String> rows = List.of("⚠ My alerts", " ➕ Place alert");
            ReplyKeyboardMarkup replyKeyboard = ReplyKeyboardHelper.buildMainMenu(rows);
            telegramService.sendMessage(request.getChatId(), "Choose from the menu below⤵ ", replyKeyboard);
        }
    }

    @Override
    public String getCallbackQuery() {
        return query;
    }

    @Override
    public boolean isInteger() {
        return false;
    }
}
