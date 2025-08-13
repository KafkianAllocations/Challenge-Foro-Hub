package com.alurachallenge.foro.hub.dto;

import com.alurachallenge.foro.hub.model.Topic;

public class TopicDataResponse {

        private Long id;
        private Long userId;
        private String curso;
        private String titulo;
        private String mensaje;
        private String fechaCreacion;

        // Constructor adecuado para crear TopicDataResponse desde TopicData
        public TopicDataResponse(TopicData topicData) {
                this.id = topicData.id(); // Accede directamente al campo id
                this.userId = topicData.usuarioId(); // Accede directamente al campo usuarioId
                this.curso = topicData.curso(); // Accede directamente al campo curso
                this.titulo = topicData.titulo(); // Accede directamente al campo titulo
                this.mensaje = topicData.mensaje(); // Accede directamente al campo mensaje
                this.fechaCreacion = topicData.fechaCreacion().toString(); // Convierte LocalDateTime a String
        }

        // Constructor para crear TopicDataResponse desde un Topic
        public TopicDataResponse(Topic topic) {
                this.id = topic.getId();
                this.userId = topic.getUsuario().getId();
                this.curso = topic.getCurso();
                this.titulo = topic.getTitulo();
                this.mensaje = topic.getMensaje();
                this.fechaCreacion = topic.getFechaCreacion().toString(); // Convierte LocalDateTime a String
        }

        // Getters y setters (si los necesitas)
        public Long getId() {
                return id;
        }

        public Long getUserId() {
                return userId;
        }

        public String getCurso() {
                return curso;
        }

        public String getTitulo() {
                return titulo;
        }

        public String getMensaje() {
                return mensaje;
        }

        public String getFechaCreacion() {
                return fechaCreacion;
        }
}
// This record is used to encapsulate topic data for response purposes,
// including