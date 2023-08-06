package com.example.telegram_api.components.impl.commandhandlers;

import com.example.telegram_api.components.abstr.UserRequestHandler;
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
public class AlertsCommandController extends UserRequestHandler {
    private static final String command = "/alerts";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        if(!session.getAuth()){
            telegramService.sendMessage(request.getChatId(), "You're need to login(1-2 mins)");
            return;
        }
        session.setState(States.ALERT_MENU);
        sessionService.saveSession(request.getChatId(), session);
        List<String> rows = List.of("⚠ My alerts", " ➕ Place alert");
        ReplyKeyboardMarkup replyKeyboard = ReplyKeyboardHelper.buildMainMenu(rows);
        telegramService.sendMessage(request.getChatId(), "Choose from the menu below⤵ ", replyKeyboard);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
