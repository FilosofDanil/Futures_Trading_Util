package com.example.telegram_api.dispatcher;

import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.components.abstr.UserRequestHandler;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Dispatcher implements IDispatcher {

    private final List<UserRequestHandler> handlers;

    /**
     * Pay attention at this constructor
     * Since we have some global handlers,
     * like command /start which can interrupt any conversation flow.
     * These global handlers should go first in the list
     */
    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers
                .stream()
                .sorted(Comparator
                        .comparing(UserRequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void dispatch(UserRequest userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if (userRequestHandler.isApplicable(userRequest)) {
                userRequestHandler.handle(userRequest);
                return;
            }
        }
    }
}
