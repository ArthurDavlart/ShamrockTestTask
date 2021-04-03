package com.example.shamrocktesttask.controller;

import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;
import com.example.shamrocktesttask.service.SmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class SmsController {
    private final SmsService smsService;
    
    public SmsController(SmsService smsService){
        this.smsService = smsService;
    } 

    @PostMapping("/sms")
    Sms send(@RequestBody Sms sms){
        return smsService.send(sms);
    }

    @PostMapping("/smses")
    List<Sms> send(@RequestBody List<Sms> smses){
        return smsService.send(smses);
    }

    @GetMapping("/smses/get")
    Set<Sms> getSmses(@RequestBody Set<String> tags){
        return smsService.getSmsesBy(tags);
    }

}
