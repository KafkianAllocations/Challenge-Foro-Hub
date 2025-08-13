package com.alurachallenge.foro.hub.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alurachallenge.foro.hub.dto.TopicData;
import com.alurachallenge.foro.hub.infra.exceptions.IntegrityValidation;
import com.alurachallenge.foro.hub.model.Topic;
import com.alurachallenge.foro.hub.repository.TopicRepository;
import com.alurachallenge.foro.hub.repository.UserRepository;

@Service
public class TopicService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    // Guarda un topico
    public TopicData publish(
            @RequestBody @Valid TopicData topicData) {
        if (!userRepository.findById(topicData.usuarioId()).isPresent()) {
            throw new IntegrityValidation("Este usuario no existe");
        }

        var user = userRepository.findById(topicData.usuarioId()).get();
        Topic topic;
        topic = new Topic(
                null,
                user,
                topicData.curso(),
                topicData.titulo(),
                topicData.mensaje(),
                topicData.fechaCreacion(),
                true);

        topicRepository.save(topic);
        return new TopicData(topic);
    }

}
