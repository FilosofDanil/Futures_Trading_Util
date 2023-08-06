package com.example.telegram_api.components.impl.commandhandlers;

import com.example.telegram_api.client.DataClient;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartCommandHandler extends UserRequestHandler {
    private final DataClient client;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private static final String command = "/start";

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        session.setState(States.CONVERSATION_STARTED);
        session.setAuth(false);
        sessionService.saveSession(request.getChatId(), session);
        telegramService.sendMessage(request.getChatId(),
                "Hello! We're glad, that you're prefer our bot, and we hope, that you'll enjoy it, and this bot come in handy for you!" +
                        " For a further usage you're need to authorize in the system. \\n " +
                        "If you haven't an account, please sign up by /signup command \\n" +
                        "If you already have it, just click /login and follow further guidelines ");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}