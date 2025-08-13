package com.alurachallenge.foro.hub.dto;

import java.time.LocalDateTime;
import com.alurachallenge.foro.hub.model.Topic;

public record TopicData(
        Long id,
        Long usuarioId, // Renombrado para que sea más claro que es el ID del usuario
        String curso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion) {

    // Constructor que convierte un Topic en un TopicData
    public TopicData(Topic topic) {
        this(
                topic.getId(),
                topic.getUsuario().getId(),
                topic.getCurso(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion());
    }
}
// This record is used to encapsulate topic data, including the ID, user ID,