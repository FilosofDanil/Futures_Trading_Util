package com.example.telegram_api.components.impl.texthandlers.registration;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.UserRequest;
import com.example.telegram_api.models.UserSession;
import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.services.functional.RegistryService;
import com.example.telegram_api.services.telegram.SessionService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmationHandler implements TextHandler {
    private final States applicable = States.SUCCESSFULLY_SIGNED_UP;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private final RegistryService registryService;
    @Override
    public void handle(UserRequest request) {
        UserSession session = request.getUserSession();
        UsernameModel usernameModel = new UsernameModel(request.getUpdate().getMessage().getChat().getUserName());
        registryService.activate(request.getUpdate().getMessage().getText(), usernameModel);
        session.setState(States.ACTIVATED);
        sessionService.saveSession(request.getChatId(), session);
        telegramService.sendMessage(request.getChatId(), "Your account has been activated, and you could authorize by just clicking /login and enjoy our service.");
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
