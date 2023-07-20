package com.example.security.services.impl;

import com.example.security.client.DataClient;
import com.example.security.model.UsernameModel;
import com.example.security.model.Users;
import com.example.security.services.AuthService;
import com.example.security.services.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MailSender mailSender;
    private final DataClient dataClient;

    @Override
    public Boolean login(String username) {
        return dataClient.getUserByName(username).getVerified();
    }

    @Override
    public void signUp(Users user) {
        user.setActivationCode(UUID.randomUUID().toString());
        dataClient.create(user);
        sendActivationCodeAssistant(user);
    }

    private void sendActivationCodeAssistant(Users user) {
        mailSender.send(user.getEmail(), "Verification", "Your Code -> " + user.getActivationCode());
    }

    @Override
    public void activate(String code, UsernameModel username) {
        Users user = dataClient.getUserByName(username.getUsername());
        System.out.println(user.getEmail());
        if (user.getActivationCode().equals(code)) {
            user.setActivationCode(null);
            user.setVerified(true);
            dataClient.update(user.getId(), user);
        }
    }
}
