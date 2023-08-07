package com.example.telegram_api.components.impl.queryhandlers;

import com.example.telegram_api.components.abstr.UserRequestHandler;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearCrossedHandler extends UserRequestHandler {
    @Override
    public boolean isApplicable(UserRequest request) {
        return isBackQuery(request.getUpdate());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {

    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
