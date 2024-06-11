package com.example.service.prag.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private  String userName;

    private String userEmail;

    private  String userPassword;
}
