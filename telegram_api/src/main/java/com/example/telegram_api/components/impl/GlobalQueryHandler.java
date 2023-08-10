package com.example.telegram_api.components.impl;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.util.ResponseParser;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GlobalQueryHandler extends UserRequestHandler {
    private final List<QueryHandler> queryHandlers;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;


    public GlobalQueryHandler(List<QueryHandler> queryHandlers, SessionService sessionService, TelegramBotService telegramService) {
        this.queryHandlers = queryHandlers
                .stream()
                .sorted(Comparator
                        .comparing(QueryHandler::isInteger))
                .collect(Collectors.toList());
        this.sessionService = sessionService;
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isBackQuery(request.getUpdate());
    }

    @Override
    public void handle(UserRequest request) {
        try {
            UserSession session = request.getUserSession();
            if (!session.getAuth()) {
                telegramService.sendMessage(request.getChatId(), "You're need to login(it'll take 1-2 mins)");
                return;
            }
            for (QueryHandler queryHandler : queryHandlers) {
                if ((queryHandler.getCallbackQuery().equals(request.getUpdate().getCallbackQuery().getData()) || queryHandler.isInteger())) {
                    queryHandler.handle(request);
                    return;
                }
            }
        } catch (FeignException ex) {
            if (ex.status() == 400) {
                UserSession session = request.getUserSession();
                String message = ResponseParser.extractMessage(ex.getMessage());
                session.setState(States.ERROR);
                sessionService.saveSession(request.getChatId(), session);
                telegramService.sendMessage(request.getChatId(), message + " Please try one more or reload bot with -> /start");
            }
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
