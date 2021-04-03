package com.example.shamrocktesttask.service;

import com.example.shamrocktesttask.model.Sms;
import com.example.shamrocktesttask.model.Tag;
import com.example.shamrocktesttask.repository.SmsRepository;
import com.example.shamrocktesttask.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SmsServiceImpl implements SmsService {
    private final SmsRepository smsRepository;
    private final TagRepository tagRepository;

    public SmsServiceImpl(SmsRepository smsRepository, TagRepository tagRepository){
        this.smsRepository = smsRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Sms send(Sms sms) {
        Set<Tag> dbTags = getDbTags(sms.getTags());

        // dbTags.forEach(tag -> tag.getSmses().add(sms));

        sms.setTags(dbTags);

        return smsRepository.save(sms);
    }

    @Override
    public List<Sms> send(List<Sms> smses) {
        Set<String> allSendingTagNames = getTags(smses);
        HashMap<String, Tag> dbTagsMap = convertToDbTagsMap(allSendingTagNames);

        convertTagsToDbTagsInSmsesByDbTagsMap(smses, dbTagsMap);

        return smsRepository.saveAll(smses);
    }

    private void convertTagsToDbTagsInSmsesByDbTagsMap(List<Sms> smses, Map<String, Tag> dbTagsMap){
        smses.forEach(sms -> {
            Set<Tag> dbTags = new HashSet();
            sms.getTags().forEach(tag -> {
                Tag dbTag = dbTagsMap.get(tag.getName());
                //dbTag.getSmses().add(sms);
                dbTags.add(dbTag);
            });
            sms.setTags(dbTags);
        });
    }

    private HashMap<String, Tag> convertToDbTagsMap(Set<String> tags){
        HashMap<String, Tag> dbTagsMap = new HashMap();

        Set<Tag> existTags = getExistTags(tags);
        existTags.forEach(tag -> dbTagsMap.put(tag.getName(), tag));

        Set<String> notExistTags =
                getNotExistTagNames(tags,
                        existTags
                                .stream()
                                .map(tag -> tag.getName()).collect(Collectors.toSet()));

        Collection<Tag> newTags = saveTags(notExistTags);
        newTags.forEach(tag -> dbTagsMap.put(tag.getName(), tag));

        return dbTagsMap;
    }

    private Set<String> getTags(List<Sms> smses){
        Set<String> tags = new HashSet();

        smses.forEach(sms -> sms.getTags().forEach(tag -> {
            if (!tags.contains(tag.getName())){
                tags.add(tag.getName());
            }
        }));

        return tags;
    }

    private Set<Tag> getDbTags(Set<Tag> tags){
        HashSet<String> tagNames = new HashSet();
        tags.forEach(tag -> tagNames.add(tag.getName()));

        Set<Tag> existTags = getExistTags(tagNames);
        HashSet<String> existTagNames = new HashSet();
        existTags.forEach(tag -> existTagNames.add(tag.getName()));

        Set<String> notExistTagNames = getNotExistTagNames(tagNames, existTagNames);

        Collection<Tag> newTag = saveTags(notExistTagNames);

        existTags.addAll(newTag);

        return existTags;
    }

    private Set<Tag> getExistTags(Set<String> tags){
        return tagRepository.findTagsByNames(tags);
    }

    private Set<String> getNotExistTagNames(Set<String> workTagNames, Set<String> existTagNames){
        return workTagNames
                .stream()
                .filter(name -> !existTagNames.contains(name))
                .collect(Collectors.toSet());
    }

    private Collection<Tag> saveTags(Set<String> notExistNames){
        return tagRepository
                .saveAll(notExistNames.stream().map(tag -> new Tag(tag)).collect(Collectors.toSet()));
    }

    @Override
    public Set<Sms> getSmsesBy(Set<String> tags) {
        return smsRepository.findAllByTagsIn(tagRepository.findTagsByNames(tags));
    }

    @Override
    public Set<Sms> getSmsesBy(Date date) {
        return null;
    }

    @Override
    public Set<Sms>getSmsesBy(String phone) {
        return null;
    }
}
