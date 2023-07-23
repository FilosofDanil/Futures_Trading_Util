package com.example.telegram_api.services.telegram;

import com.example.telegram_api.configs.BotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class BotSender extends DefaultAbsSender {

    private final BotConfig config;

    protected BotSender(BotConfig config) {
        super(new DefaultBotOptions());
        this.config = config;
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
