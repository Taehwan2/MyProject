package com.example.service.prag.user.business;

import com.example.service.prag.common.annotation.Business;
import com.example.service.prag.user.controller.model.UserRequestDto;
import com.example.service.prag.user.controller.model.UserResponse;
import com.example.service.prag.user.converter.UserConverter;
import com.example.service.prag.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    public UserResponse getUserByEmail(String email){
           return userConverter.EntityToResponse(userService.findByUserEmail(email));
    }

    public List<UserResponse> findByUserNameAndCreatedAt(String name, LocalDateTime craetedAt){
          return  userService.findByUserNameAndCreatedAt(name,craetedAt)
                    .stream().map(userConverter::EntityToResponse).collect(Collectors.toList());
    }


    public UserResponse enrollUser(UserRequestDto userRequestDto) {
        return userConverter.EntityToResponse(userService.enrollUserAll(userConverter.RequestToEntity(userRequestDto)));

    }
}
