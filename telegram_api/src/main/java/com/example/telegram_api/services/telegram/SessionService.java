package com.example.telegram_api.services.telegram;

import com.example.telegram_api.models.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SessionService {
    private Map<Long, UserSession> sessionMap;

    public UserSession getSession(Long chatId) {
        return sessionMap.getOrDefault(chatId, UserSession
                .builder()
                .chatId(chatId)
                .build());
    }

    public UserSession saveSession(Long chatId, UserSession session) {
        return sessionMap.put(chatId, session);
    }
}
