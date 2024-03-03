package com.rbt.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbt.payload.BlogDto;
import com.rbt.service.BlogService;

@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BlogService blogService;

    @Test
    void testCreateBlog() throws Exception {
        // Given
        BlogDto blogDto = new BlogDto();
        blogDto.setTitle("Test Blog");
        Integer userId = 123;

        // Stubbing the blogService.createBlog method
        Mockito.when(blogService.createBlog(any(BlogDto.class), any(Integer.class))).thenReturn(blogDto);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/{userId}/blogs", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogDto)))
                .andExpect(status().isCreated());
    }
}
