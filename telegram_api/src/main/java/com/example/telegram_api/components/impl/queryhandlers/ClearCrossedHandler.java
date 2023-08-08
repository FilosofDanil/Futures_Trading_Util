package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearCrossedHandler implements QueryHandler {
    private final static String query = "\uD83D\uDDD1 Clear crossed";

    @Override
    public void handle(UserRequest request) {

    }

    @Override
    public String getCallbackQuery() {
        return query;
    }
}
