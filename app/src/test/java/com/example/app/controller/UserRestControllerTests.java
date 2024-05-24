package com.example.app.controller;

import com.example.app.dto.UserPostDto;
import com.example.app.entity.User;
import com.example.app.repositories.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User controller testing")
public class UserRestControllerTests {

    final String URI_USER = "/user";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        user = userRepository.save(User.builder()
                .email("user1@gmail.com")
                .userName("user1")
                .password("user1123")
                .creationTime(LocalTime.of(14, 30))
                .build());

        UserPostDto userPostDTO = UserPostDto.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .creationTime(user.getCreationTime()).
                build();
    }




    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("When we create a user with valid data")
    class WhenWeCreateAUserWithValidData {

        @Test
        void shouldCreateUser() throws Exception {

            String userPostDtoJson = objectMapper.writeValueAsString(user);

            String responseJsonString = mockMvc.perform(post(URI_USER + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(userPostDtoJson))
                    .andExpect(status().isCreated()) // Codigo 201
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            User userResult = objectMapper.readValue(responseJsonString, User.class);

            assertAll(
                    () -> assertNotNull(userResult.getId()),
                    () -> assertEquals(userResult.getUserName(), userResult.getUserName())
            );
        }
    }
}
