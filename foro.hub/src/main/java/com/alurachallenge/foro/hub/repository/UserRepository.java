package com.alurachallenge.foro.hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alurachallenge.foro.hub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);
}
// This interface extends JpaRepository to provide CRUD operations for User
// entities.
// It also includes a method to find a user by their username, returning a
// UserDetails object.
