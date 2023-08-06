package com.example.telegram_api.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ReplyKeyboardHelper {
    public static ReplyKeyboardMarkup buildMainMenu(List<String> rows) {
        KeyboardRow keyboardRow = new KeyboardRow();
        rows.forEach(keyboardRow::add);
        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
