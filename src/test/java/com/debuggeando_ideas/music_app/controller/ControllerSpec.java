package com.debuggeando_ideas.music_app.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//@WebMvcTest
//@WebMvcTest(value = AlbumController.class) // Para testear un unico controller
@WebMvcTest(value = { AlbumControllerTest.class, TrackController.class }) // Para varios controllers
public class ControllerSpec {
}
