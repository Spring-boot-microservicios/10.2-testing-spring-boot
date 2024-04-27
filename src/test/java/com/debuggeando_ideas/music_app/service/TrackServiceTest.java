package com.debuggeando_ideas.music_app.service;

import com.debuggeando_ideas.music_app.DataDummy;
import com.debuggeando_ideas.music_app.entity.TrackEntity;
import com.debuggeando_ideas.music_app.repository.TrackRepository;
import com.debuggeando_ideas.music_app.service.impl.TrackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test") // Para obtener la configuracion .properties .yml
public class TrackServiceTest {

    @Mock
    private TrackRepository trackRepositoryMock;

    @InjectMocks
    private TrackServiceImpl trackService;

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = 2L;

    @BeforeEach
    public void SetMocks() {
        when(this.trackRepositoryMock.findById(VALID_ID))
                .thenReturn(Optional.of(DataDummy.TRACK_1));

        when(this.trackRepositoryMock.findById(INVALID_ID))
                .thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("findById should works")
    public void findById() {
        TrackEntity result = this.trackService.findById(VALID_ID);
        assertEquals(DataDummy.TRACK_1, result);

        assertThrows(NoSuchElementException.class,
            () -> this.trackService.findById(INVALID_ID)
        );
    }
    
}
