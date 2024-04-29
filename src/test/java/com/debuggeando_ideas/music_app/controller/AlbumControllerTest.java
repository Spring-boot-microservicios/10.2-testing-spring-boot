package com.debuggeando_ideas.music_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class AlbumControllerTest extends ControllerSpec {

    @Autowired
    private MockMvc mockMvc; // Simulando el servidor

    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.objectMapper = new ObjectMapper(); // Transformar el json a Objetos para hacer test
    }

}
