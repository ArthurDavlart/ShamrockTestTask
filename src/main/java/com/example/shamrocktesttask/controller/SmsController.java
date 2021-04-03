package com.example.shamrocktesttask.controller;

import com.example.shamrocktesttask.dto.IntervalDto;
import com.example.shamrocktesttask.dto.SmsDto;
import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;
import com.example.shamrocktesttask.service.SmsService;
import com.example.shamrocktesttask.utils.Convertor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SmsController {
    private final SmsService smsService;
    private final Convertor convertor;
    
    public SmsController(SmsService smsService, Convertor convertor){
        this.smsService = smsService;
        this.convertor = convertor;
    }

    @PostMapping("/sms")
    SmsDto send(@RequestBody Sms sms){
        return convertor.convertToSmsDto(smsService.send(sms));
    }

    @PostMapping("/smses")
    List<SmsDto> send(@RequestBody List<Sms> smses){
        return smsService.send(smses)
                .stream()
                .map(sms -> convertor.convertToSmsDto(sms)).collect(Collectors.toList());
    }

    @GetMapping("/smses/getByTag")
    List<SmsDto> getSmses(@RequestBody Set<String> tags){
        return smsService.getSmsesBy(tags)
                .stream()
                .map(sms -> convertor.convertToSmsDto(sms))
                .collect(Collectors.toList());
    }

    @GetMapping("/smses/getByPhone")
    List<SmsDto> getSmses(@RequestBody String phone){
        return smsService.getSmsesBy(phone)
                .stream()
                .map(sms -> convertor.convertToSmsDto(sms))
                .collect(Collectors.toList());
    }

    @GetMapping("/smses/getByTime")
    List<SmsDto> getSmses(@RequestBody IntervalDto intervalDto){
        return smsService.getSmsesBy(intervalDto.getStartDate(), intervalDto.getFinishDate())
                .stream()
                .map(sms -> convertor.convertToSmsDto(sms))
                .collect(Collectors.toList());
    }

}
