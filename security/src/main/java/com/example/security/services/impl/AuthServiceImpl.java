package com.example.security.services.impl;

import com.example.security.model.Users;
import com.example.security.services.AuthService;
import com.example.security.services.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MailSender mailSender;

    @Override
    public void login(Users user) {

    }

    @Override
    public void signUp(Users user) {

    }
}
