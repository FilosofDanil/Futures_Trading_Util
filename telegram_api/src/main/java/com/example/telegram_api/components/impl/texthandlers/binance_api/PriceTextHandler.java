package com.example.telegram_api.components.impl.texthandlers.binance_api;

import com.example.telegram_api.components.abstr.TextHandler;
import com.example.telegram_api.enums.States;
import com.example.telegram_api.models.telegram_entities.UserRequest;
import com.example.telegram_api.services.functional.PriceService;
import com.example.telegram_api.services.telegram.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceTextHandler implements TextHandler {
    private final States applicable = States.WAITING_FOR_TICKER;

    private final TelegramBotService telegramService;

    private final PriceService priceService;

    @Override
    public void handle(UserRequest request) {
        String ticker = request.getUpdate().getMessage().getText();
        String response = priceService.getPrice(ticker);
        telegramService.sendMessage(request.getChatId(), "The current price is "+response);
    }

    @Override
    public States getApplicableState() {
        return this.applicable;
    }
}
