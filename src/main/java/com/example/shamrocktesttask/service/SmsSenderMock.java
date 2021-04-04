package com.example.shamrocktesttask.service;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SmsSenderMock implements SmsSender {
    @Override
    public Date sendMessage(String phone, String message) {
        return new Date();
    }
}
