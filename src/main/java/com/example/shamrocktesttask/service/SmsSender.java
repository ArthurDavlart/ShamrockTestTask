package com.example.shamrocktesttask.service;

import java.util.Date;

public interface SmsSender {
    Date sendMessage(String phone, String message);
}
