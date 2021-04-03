package com.example.shamrocktesttask.service;

import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SmsService {
    Sms send(Sms sms);
    List<Sms> send(List<Sms> smses);
    List<Sms> getSmsesBy(Date start, Date finish);
    List<Sms> getSmsesBy(String phone);
    List<Sms> getSmsesBy(Set<String> tags);
}
