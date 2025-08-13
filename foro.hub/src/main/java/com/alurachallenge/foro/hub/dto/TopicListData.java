package com.alurachallenge.foro.hub.dto;

import java.time.LocalDateTime;

import com.alurachallenge.foro.hub.model.Topic;

public record TopicListData(
        Long id,
        String curso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status) {
    public TopicListData(Topic topic) {
        this(
                topic.getId(),
                topic.getCurso(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus());
    }
}
// title, message, creation date, and status of a topic.
