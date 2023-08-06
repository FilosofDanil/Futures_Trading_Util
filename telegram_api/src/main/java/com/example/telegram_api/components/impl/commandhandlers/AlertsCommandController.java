package com.example.telegram_api.components.impl.commandhandlers;

import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public void handle(UserRequest dispatchRequest) {

    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
