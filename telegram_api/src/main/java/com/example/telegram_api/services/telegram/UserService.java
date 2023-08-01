package com.example.telegram_api.services.telegram;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private Map<String, Long> userMap;

    public void saveUser(String username, Long chatId) {
        userMap.put(username, chatId);
    }

    public Long getChatId(String username) {
        System.out.println(userMap.get(username));
        return userMap.get(username);
    }
}
