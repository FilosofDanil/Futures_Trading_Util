package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.QueryHandler;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertsManageIntHandler implements QueryHandler {
    private static final String query = "";


    @Override
    public void handle(UserRequest request) {

    }

    @Override
    public String getCallbackQuery() {
        return query;
    }

    @Override
    public boolean isInteger() {
        return true;
    }
}
