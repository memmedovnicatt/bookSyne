package com.example.booksyne.controller;

import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.servis.BagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@AutoConfigureMockMvc
class BagControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BagService bagService;
    private BagResponse bagResponse;

    @BeforeEach
    void setUp() {
        bagResponse = new BagResponse();
        bagResponse.setId(1);
        bagResponse.setType("Handbag");
        bagResponse.setPrice(12.0);
    }

    @AfterEach
    void tearDown() {
        bagResponse = null;
    }


    @Test
    void givenIdThenReturnBagResponseDtoThenOk() throws Exception {
        when(bagService.getById(anyInt())).thenReturn(bagResponse);

        mockMvc.perform(get("/bags/{id}", 1)
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Handbag"))
                .andExpect(jsonPath("$.price").value(12.0))
                .andDo(print());
        verify(bagService, times(1)).getById(anyInt());
    }
}