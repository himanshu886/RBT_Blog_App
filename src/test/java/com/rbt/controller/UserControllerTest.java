package com.rbt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbt.payload.UserDto;
import com.rbt.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        // Given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Himanshu");
        userDto.setLastName("Thakre");
        userDto.setAbout("I am software Engineer");
        
        // Stubbing the userService.createUser method
        Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(userDto);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
