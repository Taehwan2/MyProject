package com.example.service.prag.user.service;

import com.example.service.prag.user.domain.User;
import com.example.service.prag.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "schedules", key = "#scheduleId + '-' + #memberId")
    public User findByUserEmail(String email){
       return  userRepository.findByUserEmail(email);
    }


    @Transactional(readOnly = true)
    public List<User> findByUserNameAndCreatedAt(String name, LocalDateTime createdAt){
        return  userRepository.findByUserNameAndCreatedAt(name, createdAt);
    }

    @Transactional
    public User enrollUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public User enrollUserPrag(User user){
        for(int i=0;i<10000;i++) {
            var user2 = User.builder()
                    .userName(user.getUserName()+i)
                    .userPassword(user.getUserPassword())
                    .userEmail(user.getUserEmail()).build();
            userRepository.save(user2);
        }
       return  userRepository.save(user);
    }

    @Transactional
    public User enrollUserAll(User user){
        List<User> userList =new ArrayList<>();
        for(int i=0;i<10000;i++) {
            var user2 = User.builder()
                    .userName(user.getUserName()+i)
                    .userPassword(user.getUserPassword())
                    .userEmail(user.getUserEmail()).build();
            userList.add(user2);
        }
        userRepository.saveAll(userList);
        return userRepository.save(userList.get(0));
    }

}
