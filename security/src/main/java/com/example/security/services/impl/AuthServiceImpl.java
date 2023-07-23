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
    public Boolean login(UsernameModel username) {
        if (dataClient.getUserByName(username.getUsername()) == null) {
            return Boolean.FALSE;
        }
        return dataClient.getUserByName(username.getUsername()).getVerified();
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
        System.out.println(user.toString());
        if (user.getActivationCode().equals(code)) {
            user.setActivationCode(null);
            user.setVerified(true);
            System.out.println(user.toString());
            dataClient.update(user.getId(), user);
        }
    }
}
