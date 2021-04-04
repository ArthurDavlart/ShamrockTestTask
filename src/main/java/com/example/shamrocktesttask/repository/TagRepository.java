package com.example.shamrocktesttask.repository;

import com.example.shamrocktesttask.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findAllByNameIn(Iterable<String> names);
}
