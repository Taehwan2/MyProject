package com.example.service.prag.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserEmail(String email);

    List<User> findByUserNameAndCreatedAt(String name, LocalDateTime createdAt);
}
