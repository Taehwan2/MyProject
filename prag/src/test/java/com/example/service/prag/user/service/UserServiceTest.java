package com.example.service.prag.user.service;

import com.example.service.prag.user.domain.User;
import com.example.service.prag.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void findUserByEmail(){

        User user = User.builder()
                .userName("Taehwan")  // user_name 필드 추가
                .userEmail("Taehwan@gmail.com")  // user_email 필드 추가
                .userPassword("password")  // user_password 필드 추가
                .build();

    }


    @Test
    public void findUserByEmai2() {
        // 테스트할 사용자 객체 생성
        User user = User.builder()
                .userName("Taehwan")
                .userEmail("Taehwan@gmail.com")
                .userPassword("password")
                .build();

        // userRepository.findByUserEmail 호출 시 반환값 설정
        when(userRepository.findByUserEmail("Taehwan@gmail.com")).thenReturn(user);

        // 서비스 메서드 호출
        User foundUser = userService.findByUserEmail("Taehwan@gmail.com");

        // 검증
        assertNotNull(foundUser);
        assertEquals("Taehwan", foundUser.getUserName());
        assertEquals("Taehwan@gmail.com", foundUser.getUserEmail());
        assertEquals("password", foundUser.getUserPassword());

        // userRepository.findByUserEmail 메서드가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).findByUserEmail("Taehwan@gmail.com");
    }

    @Test
    public void findByUserNameAndCreatedAtTest(){
        User user = User.builder()
                .userName("Taehwan")
                .userEmail("Taehwan@gmail.com")
                .userPassword("password")
                .build();

        User user2 = User.builder()
                .userName("Taehwan")
                .userEmail("Taehwan2@gmail.com")
                .userPassword("password2")
                .build();

        List<User> users = List.of(user2,user);
        String name = "Taehwan";
        LocalDateTime createdAt=LocalDateTime.now();

        when(userRepository.findByUserNameAndCreatedAt(name,createdAt)).thenReturn(users);

        List<User> result = userService.findByUserNameAndCreatedAt(name,createdAt);

        assertNotNull(result);
        assertEquals("Taehwan", result.get(0).getUserName());
//        assertEquals("Taehwan@gmail.com", result.get(0).getUserEmail());
        // userRepository.findByUserEmail 메서드가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).findByUserNameAndCreatedAt(name,createdAt);
    }
}