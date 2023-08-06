package com.example.telegram_api.components.impl.commandhandlers;

import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriceCommandHandler extends UserRequestHandler {
    private static final String command = "/price";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        if (request.getUserSession().getState() != null) {
            UserSession session = request.getUserSession();
            session.setState(States.WAITING_FOR_TICKER);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "Send ticker⤵️");
        } else{
            telegramService.sendMessage(request.getChatId(), "You need to start bot -> /start");
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
