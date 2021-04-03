package com.example.shamrocktesttask.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique=true)
    private String name;

    @ManyToMany
    private Set<Sms> smses = new HashSet();

    public Tag() {

    }

    public Tag(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Sms> getSmses() {
        return smses;
    }

    public void setSmses(Set<Sms> smses) {
        this.smses = smses;
    }

}
