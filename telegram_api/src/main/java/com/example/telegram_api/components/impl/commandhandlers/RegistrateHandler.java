package com.example.telegram_api.components.impl.commandhandlers;

import com.example.telegram_api.components.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrateHandler extends UserRequestHandler {
    private static final String command = "/signup";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        if (request.getUserSession().getState() != null) {
            UserSession session = request.getUserSession();
            session.setState(States.WAITING_FOR_MAIL);
            sessionService.saveSession(request.getChatId(), session);
            telegramService.sendMessage(request.getChatId(), "Send your mail⤵️");
        } else{
            telegramService.sendMessage(request.getChatId(), "You need to start bot -> /start");
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
