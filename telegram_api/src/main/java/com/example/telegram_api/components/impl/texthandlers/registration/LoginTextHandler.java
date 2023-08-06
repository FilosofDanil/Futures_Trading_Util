package com.example.telegram_api.components.impl.texthandlers.registration;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.LoginRequest;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.models.telegram_entities.UserSession;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginTextHandler implements TextHandler {
    private final States applicable = States.LOGIN_WAIT_PASSWORD;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;

    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        if (!session.getAuth()) {
            LoginRequest loginRequest = LoginRequest.builder()
                    .name(request.getUpdate().getMessage().getChat().getUserName())
                    .password(request.getUpdate().getMessage().getText())
                    .build();
            if (registryService.login(loginRequest)) {
                session.setState(States.SUCCESSFULLY_LOGIN);
                session.setAuth(true);
                sessionService.saveSession(request.getChatId(), session);
                telegramService.sendMessage(request.getChatId(), "You've been successfully login, now you can use all bot functions!");
            } else {
                telegramService.sendMessage(request.getChatId(), "Fail Authorization. Wrong password or username, please try again!");
            }

        }
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
