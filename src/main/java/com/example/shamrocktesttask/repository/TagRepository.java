package com.example.shamrocktesttask.repository;

import com.example.shamrocktesttask.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("select t from Tag t where t.name in :names")
    Set<Tag> findTagsByNames(@Param("names")Iterable<String> names);
}
