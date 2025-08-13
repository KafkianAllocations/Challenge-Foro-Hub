package com.alurachallenge.foro.hub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alurachallenge.foro.hub.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    // Method to find all topics with status true, paginated
    Page<Topic> findByStatusTrue(Pageable pageable);
}
