package com.example.service.prag.user.converter;

import com.example.service.prag.common.annotation.Converter;
import com.example.service.prag.user.controller.model.UserRequestDto;
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

    //TODO ErrorCode 개인이 원하는 방향으로 리펙토링하기
    public User RequestToEntity(UserRequestDto userRequestDto){
        return Optional.ofNullable(userRequestDto).
                map(
                        it -> {
                            return User.builder()
                                    .userName(userRequestDto.getUserName())
                                    .userEmail(userRequestDto.getUserEmail())
                                    .userPassword(userRequestDto.getUserPassword())
                                    .build();
                        }
                )
                .orElseThrow(()-> new NoSuchElementException("no element"));
    }
}
