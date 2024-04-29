package com.debuggeando_ideas.music_app.controller;

import com.debuggeando_ideas.music_app.DataDummy;
import com.debuggeando_ideas.music_app.service.IAlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AlbumControllerStatus200Test extends AlbumControllerSpec {

    @Autowired
    private MockMvc mockMvc; // Simulando el servidor

    @MockBean
    private IAlbumService albumServiceMock;

    private ObjectMapper objectMapper;

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = 2L;
    private static final String RESOURCE_PATH = "/v1/album";

    @BeforeEach
    public void init() {
        this.objectMapper = new ObjectMapper(); // Transformar el json a Objetos para hacer test
    }

    @BeforeEach
    public void setupMocks() {
        when(this.albumServiceMock.findById(eq(VALID_ID)))
                .thenReturn(DataDummy.ALBUM_DTO);
    }

    @Test
    @DisplayName("call findById should works")
    public void findById() throws Exception {
        final String url = RESOURCE_PATH + "/" + VALID_ID;

        this.mockMvc.perform(
            get(url).contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());

     }

}