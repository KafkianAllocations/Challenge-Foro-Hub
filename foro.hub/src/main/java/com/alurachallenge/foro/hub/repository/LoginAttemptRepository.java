package com.alurachallenge.foro.hub.repository;

import com.alurachallenge.foro.hub.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
}
