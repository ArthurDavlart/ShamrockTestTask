package com.example.shamrocktesttask.dto;

import java.util.Date;
import java.util.Set;

public class SmsDto {
    private String message;
    private String phone;
    private Date sendingTime;
    private Set<String> tags;

    public SmsDto() {
    }

    public SmsDto(String message, String phone, Date sendingTime, Set<String> tags) {
        this.message = message;
        this.phone = phone;
        this.sendingTime = sendingTime;
        this.tags = tags;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
