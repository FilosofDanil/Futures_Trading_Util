package com.example.telegram_api.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardHelper {
    public static InlineKeyboardMarkup buildInlineKeyboard(List<String> rows) {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        // Set the keyboard to the markup

        rows.forEach(row ->{
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData(row);
            button.setText(row);
            rowInline.add(button);
        });
        rowsInline.add(rowInline);
        return InlineKeyboardMarkup.builder()
                .keyboard(rowsInline)
                .build();


    }
}
