package com.example.telegram_api.components.impl.texthandlers.navigation;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.functional.PriceService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ReplyKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static com.example.telegram_api.enums.States.CLEARED_ALERTS;

@Component
@RequiredArgsConstructor
public class BackButtonHandler implements TextHandler {
    private final States applicable = CLEARED_ALERTS;

    private final TelegramBotService telegramService;

    private final SessionService sessionService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        if (request.getUpdate().getMessage().getText().equals("\uD83D\uDD19 Back")){
            session.setState(States.ALERT_MENU);
            sessionService.saveSession(request.getChatId(), session);
            List<String> rows = List.of("⚠ My alerts", " ➕ Place alert");
            ReplyKeyboardMarkup replyKeyboard = ReplyKeyboardHelper.buildMainMenu(rows);
            telegramService.sendMessage(request.getChatId(), "Choose from the menu below⤵ ", replyKeyboard);
        }
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
