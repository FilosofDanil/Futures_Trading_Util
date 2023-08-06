package com.example.telegram_api.components.abstr;

import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;

public interface TextHandler {
    void handle(UserRequest request);

    States getApplicableState();
}
