package com.joe.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.oauth2.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
    Optional<Users> findByEmail(String email);
}
