package com.example.shamrocktesttask.repository;

import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    Set<Sms> findAllByTagsIn(Set<Tag> tags);
    Set<Sms> findAllByPhone(String phone);
    Set<Sms> findAllBySendingTimeBetween(Date startDate, Date EndDate);
}
