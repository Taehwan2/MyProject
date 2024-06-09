package com.example.service.prag.user.controller;

import com.example.service.prag.user.business.UserBusiness;
import com.example.service.prag.user.controller.model.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@WebMvcTest(controllers = UserApiController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBusiness userBusiness;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserApiController(userBusiness)).build();
    }

    @Test
    void getUserByEmail() throws Exception {
        // Mock the business method using when().thenReturn()
        UserResponse userResponse = new UserResponse(1L,"Taehwan", "Taehwan@gmail.com", LocalDateTime.now(),LocalDateTime.now());
        when(userBusiness.getUserByEmail("Taehwan@gmail.com")).thenReturn(userResponse);

        mockMvc.perform(get("/api_user/Taehwan@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userName").value("Taehwan"))
                .andExpect(jsonPath("$.userEmail").value("Taehwan@gmail.com"));
    }

    @Test
    void getUserByEmailTest() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.toString(); // ISO-8601 형식 문자열

        UserResponse userResponse = new UserResponse(1L,"Taehwan", "Taehwan@gmail.com", now,now);
        UserResponse userResponse2 = new UserResponse(2L,"Taehwan", "Taehwan@gmail.com", now,now);
        List<UserResponse> userResponseList = List.of(userResponse2,userResponse);
        when(userBusiness.findByUserNameAndCreatedAt("Taehwan",now)).thenReturn(userResponseList);

        mockMvc.perform(get("/api_user/Taehwan/" + nowString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$[0].userName").value("Taehwan"))
                .andExpect(jsonPath("$[0].userEmail").value("Taehwan@gmail.com"))
                .andExpect(jsonPath("$[1].userName").value("Taehwan"))
                .andExpect(jsonPath("$[1].userEmail").value("Taehwan@gmail.com"));

    }

}