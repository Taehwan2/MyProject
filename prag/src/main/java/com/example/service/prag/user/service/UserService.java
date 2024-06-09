package com.example.service.prag.user.service;

import com.example.service.prag.user.domain.User;
import com.example.service.prag.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByUserEmail(String email){
       return  userRepository.findByUserEmail(email);
    }


    @Transactional(readOnly = true)
    public List<User> findByUserNameAndCreatedAt(String name, LocalDateTime createdAt){
        return  userRepository.findByUserNameAndCreatedAt(name, createdAt);
    }


}
