package com.example.service.prag.user.converter;

import com.example.service.prag.common.annotation.Converter;
import com.example.service.prag.user.controller.model.UserResponse;
import com.example.service.prag.user.domain.User;

import java.util.NoSuchElementException;
import java.util.Optional;

@Converter
public class UserConverter {

    public UserResponse EntityToResponse(User user) {
        return  Optional.ofNullable(user)
                .map(
                        it -> {
                            return UserResponse.builder()
                                    .userId(it.getUserId())
                                    .userName(it.getUserName())
                                    .userEmail(it.getUserEmail())
                                    .createdAt(it.getCreatedAt())
                                    .updatedAt(it.getUpdatedAt())
                                    .build();
                        }
                )
                .orElseThrow(() -> new NoSuchElementException("no element"));
    }
}
