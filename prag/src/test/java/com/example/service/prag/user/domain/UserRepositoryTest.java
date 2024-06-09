package com.example.service.prag.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.NoSuchElementException;

@DataJpaTest
@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void testCreate(){
        User user = User.builder()
                .userName("Taehwan")  // user_name 필드 추가
                .userEmail("Taehwan@gmail.com")  // user_email 필드 추가
                .userPassword("password")  // user_password 필드 추가
                .build();

        User user2 = userRepository.save(user);

        Assertions.assertEquals(user2.getUserId(),1L);
        Assertions.assertTrue(user.getUserName().equals("Taehwan"));

    }

    @Test
    void testFindOneException() {
        // 예외가 발생할 것으로 기대되는 코드를 전달
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Nonon"));
        });
    }

    @Test
    void testFindOne() {
        // 예외가 발생할 것으로 기대되는 코드를 전달
        User user = User.builder()
                .userName("Taehwan")  // user_name 필드 추가
                .userEmail("Taehwan@gmail.com")  // user_email 필드 추가
                .userPassword("password")  // user_password 필드 추가
                .build();

        User user2 = userRepository.save(user);
        User user3 = userRepository.findById(1L).get();

        Assertions.assertEquals(user3.getUserPassword(),"password");
    }

    @Test
    void testFindOne2() {
        // 사용자 생성 및 저장
        User user = User.builder()
                .userName("Taehwan")
                .userEmail("Taehwan@gmail.com")
                .userPassword("password")
                .build();

        User user2 = userRepository.save(user);

        // 저장된 사용자 ID 확인
        Long savedUserId = user2.getUserId();
        System.out.println("Saved user ID: " + savedUserId);
        testEntityManager.clear();
        // 저장된 사용자 ID로 조회
        User user3 = userRepository.findById(savedUserId).orElseThrow(() -> new NoSuchElementException("User not found"));
        System.out.println("Fetched user ID: " + user3.getUserId());

        // Assertions
        Assertions.assertEquals("password", user3.getUserPassword());
    }

}