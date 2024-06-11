package com.example.service.prag.user.controller;


import com.example.service.prag.user.business.UserBusiness;
import com.example.service.prag.user.controller.model.UserRequestDto;
import com.example.service.prag.user.controller.model.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api_user")
public class UserApiController {
    private final UserBusiness  userBusiness;

    @PostMapping("/enroll")
    public ResponseEntity<UserResponse> enrollUser(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity(userBusiness.enrollUser(userRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/{email}")
    /*ResponseEntity는 리펙토링 대상*/
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable(name = "email") String email){
       return new ResponseEntity(userBusiness.getUserByEmail(email), HttpStatus.ACCEPTED);

    }

    @GetMapping("/{name}/{createdAt}")
    public ResponseEntity<List<UserResponse>> getUserByEmail(@PathVariable(name = "name") String name, @PathVariable(name = "createdAt") LocalDateTime createdAt){
        return new ResponseEntity(userBusiness.findByUserNameAndCreatedAt(name,createdAt), HttpStatus.ACCEPTED);

    }


}
