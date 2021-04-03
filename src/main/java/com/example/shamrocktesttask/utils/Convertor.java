package com.example.shamrocktesttask.utils;

import com.example.shamrocktesttask.dto.SmsDto;
import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Convertor {
    private final ModelMapper modelMapper;

    public Convertor(){
        this.modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Sms.class, SmsDto.class)
        .addMapping(Sms:: getTagNames, SmsDto:: setTags);
    }

    public Sms convertToSms(SmsDto sms){
        return null;
    }

    public SmsDto convertToSmsDto(Sms sms){
        return modelMapper.map(sms, SmsDto.class);
    }

    public List<Sms> convertToSms(List<SmsDto> smsDtos){
        return null;
    }

    public List<SmsDto> convertToSmsDto(List<Sms> smses){
        return null;
    }
}
