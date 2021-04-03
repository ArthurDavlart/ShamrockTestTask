package com.example.shamrocktesttask.service;

import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SmsService {
    Sms send(Sms sms);
    List<Sms> send(List<Sms> smses);
    Set<Sms> getSmsesBy(Date date);
    Set<Sms> getSmsesBy(String phone);
    Set<Sms> getSmsesBy(Set<String> tags);
}
