package com.example.telegram_api.dispatcher;

import com.example.telegram_api.models.UserRequest;

public interface IDispatcher {
    void dispatch(UserRequest userRequest);
}
