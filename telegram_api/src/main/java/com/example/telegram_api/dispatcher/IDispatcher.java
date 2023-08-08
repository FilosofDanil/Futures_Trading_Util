package com.example.telegram_api.dispatcher;

import com.example.telegram_api.models.telegram_entities.UserRequest;

public interface IDispatcher {
    void dispatch(UserRequest userRequest);
}
