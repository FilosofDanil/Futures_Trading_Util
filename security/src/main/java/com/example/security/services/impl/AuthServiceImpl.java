package com.example.security.services.impl;

import com.example.security.client.DataClient;
import com.example.security.model.*;
import com.example.security.services.AuthService;
import com.example.security.services.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MailSender mailSender;
    private final DataClient dataClient;

    @Override
    public Boolean login(LoginRequest loginRequest) {
        Users user = dataClient.getUserByName(loginRequest.getProfileName());
        if (user == null) {
           throw new NullPointerException("This account isn't exist, please sign up in our service: click /signup");
        } if(!user.getVerified()){
            return Boolean.FALSE;
        } if(!Objects.equals(user.getPassword(), loginRequest.getPassword())){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
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
        if (user.getActivationCode().equals(code)) {
            user.setActivationCode(null);
            user.setVerified(true);
            dataClient.update(user.getId(), user);
        }
    }
}
