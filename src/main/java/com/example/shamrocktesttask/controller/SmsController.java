package com.example.shamrocktesttask.controller;

import com.example.shamrocktesttask.dto.IntervalDto;
import com.example.shamrocktesttask.dto.SmsDto;
import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.service.SmsService;
import com.example.shamrocktesttask.utils.Convertor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SmsController {
    private final SmsService smsService;
    private final Convertor convertor;
    
    public SmsController(SmsService smsService, Convertor convertor){
        this.smsService = smsService;
        this.convertor = convertor;
    }

    @PostMapping("/sms")
    ResponseEntity<?> send(@RequestBody SmsDto smsDto){
        Sms sms = convertor.convertToSms(smsDto);
        SmsDto smsDtoResult = convertor.convertToSmsDto(smsService.send(sms));

        EntityModel<SmsDto> entityModel = toModel(smsDtoResult);

        return  ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/smses")
    ResponseEntity<?> send(@RequestBody List<SmsDto> smsDtos){
        List<Sms> smses = smsDtos
                .stream()
                .map(smsDto -> convertor.convertToSms(smsDto))
                .collect(Collectors.toList());

        List<Sms> smsesResult = smsService.send(smses);

        List<Long> ids = smsesResult
                .stream()
                .map(Sms:: getId)
                .collect(Collectors.toList());

        List<EntityModel<SmsDto>> smsDtoResult = smsesResult
                .stream()
                .map(sms -> toModel(convertor.convertToSmsDto(sms)))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<SmsDto>> entityModels =  CollectionModel.of(smsDtoResult,
                linkTo(methodOn(SmsController.class).getSmsesByIds(ids)).withSelfRel());

        return ResponseEntity
                .created(entityModels.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(smsDtoResult);
    }

    @GetMapping("/sms/getById/{id}")
    ResponseEntity<?> getSmsById(@PathVariable Long id){
        SmsDto smsdto = convertor.convertToSmsDto(smsService.getSmsById(id));

        EntityModel<SmsDto> smsDtoEntityModel = toModel(smsdto);

        return ResponseEntity
                .ok(smsDtoEntityModel);
    }

    @GetMapping("/smses/getSmsesByIds")
    ResponseEntity<?> getSmsesByIds(@RequestBody List<Long> ids){
        List<EntityModel<SmsDto>> entityModels = smsService.getSmsesByIds(ids)
                .stream()
                .map(sms -> toModel(convertor.convertToSmsDto(sms)))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(entityModels);
    }

    @GetMapping("/smses/getByTags")
    ResponseEntity<?> getSmsesByTags(@RequestBody Set<String> tags){
        return ResponseEntity
                .ok(smsService.getSmsesByTags(tags)
                .stream()
                .map(sms -> toModel(convertor.convertToSmsDto(sms)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/smses/getByPhone")
    ResponseEntity<?> getSmsesByPhone(@RequestBody String phone){
        return ResponseEntity
                .ok(smsService.getSmsesByPhone(phone)
                        .stream()
                        .map(sms -> toModel(convertor.convertToSmsDto(sms)))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/smses/getByTime")
    ResponseEntity<?> getSmsesByTime(@RequestBody IntervalDto intervalDto){
        return ResponseEntity
                .ok(smsService.getSmsesByTime(intervalDto.getStartDate(), intervalDto.getFinishDate())
                        .stream()
                        .map(sms -> toModel(convertor.convertToSmsDto(sms)))
                        .collect(Collectors.toList()));
    }

    private EntityModel<SmsDto> toModel(SmsDto smsDto){
        return EntityModel.of(smsDto,
                linkTo(methodOn(SmsController.class).getSmsById(smsDto.getId())).withSelfRel());
    }
}
