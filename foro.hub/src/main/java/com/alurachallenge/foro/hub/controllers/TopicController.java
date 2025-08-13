package com.alurachallenge.foro.hub.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alurachallenge.foro.hub.dto.*;
import com.alurachallenge.foro.hub.infra.exceptions.ResourceNotFoundException;
import com.alurachallenge.foro.hub.model.Topic;
import com.alurachallenge.foro.hub.repository.TopicRepository;
import com.alurachallenge.foro.hub.service.TopicService;

@RestController
@RequestMapping("topics")
public class TopicController {

    private final TopicService service;
    private final TopicRepository repository;

    public TopicController(TopicService service, TopicRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // POST new topic to DB
    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse> setTopic(@RequestBody @Valid TopicData topic) {
        TopicData createdTopic = service.publish(topic);
        return buildResponse(HttpStatus.CREATED, new TopicDataResponse(createdTopic));
    }

    // Fetch all topics
    @GetMapping
    public Page<TopicListData> getTopics(
            @PageableDefault(size = 5, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findByStatusTrue(pageable).map(TopicListData::new);
    }

    // Fetch a specific topic by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getATopic(@PathVariable Long id) {
        Topic topic = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic", "id", id));
        TopicData topicData = new TopicData(topic); // Asumiendo que TopicData tiene un constructor que acepta Topic
        TopicDataResponse response = new TopicDataResponse(topicData); // Usando el nuevo constructor

        return buildResponse(HttpStatus.OK, response);
    }

    // PUT Update a topic in DB
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> updateTopic(@PathVariable Long id,
            @RequestBody @Valid TopicDataUpdate topicData) {
        Topic topic = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic", "id", id));
        topic.updateTopicData(topicData);
        TopicDataResponse response = new TopicDataResponse(topic);
        return buildResponse(HttpStatus.OK, response);
    }

    // DELETE a topic from DB
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteTopic(@PathVariable Long id) {
        Topic topic = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic", "id", id));
        topic.disableTopic();
        return buildResponse(HttpStatus.NO_CONTENT, "Topic deleted successfully");
    }

    // Helper method to build responses
    private ResponseEntity<ApiResponse> buildResponse(HttpStatus status, Object data) {
        ApiResponse response = new ApiResponse(status.value(), data);
        return new ResponseEntity<>(response, status);
    }

    // Helper class for standardized API responses
    public static class ApiResponse {
        private final int httpStatus;
        private final Object data;

        public ApiResponse(int httpStatus, Object data) {
            this.httpStatus = httpStatus;
            this.data = data;
        }

        public int getHttpStatus() {
            return httpStatus;
        }

        public Object getData() {
            return data;
        }
    }
}
// This class is responsible for handling HTTP requests related to topics in the
// forum.