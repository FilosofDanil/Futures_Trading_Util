package com.example.telegram_api.controllers;

import com.example.telegram_api.models.telegram_entities.Message;
import com.example.telegram_api.services.telegram.TelegramBotService;
import com.example.telegram_api.services.telegram.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/telegram")
public class MessageReceiver {
    private final TelegramBotService botService;
    private final UserService userService;

    @PostMapping("")
    public void receive(@RequestBody Message message) {
        System.out.println(message.toString());
        botService.sendMessage(userService.getChatId(message.getProfileName()), message.getMessage());
    }
}
