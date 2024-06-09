package com.example.service.prag.user.controller.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse
{
    private Long userId;

    private  String userName;

    private String userEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
