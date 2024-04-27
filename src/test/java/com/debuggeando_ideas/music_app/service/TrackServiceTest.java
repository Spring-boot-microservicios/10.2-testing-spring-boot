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
import java.util.Set;

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

    @Test
    @DisplayName("getAll should works")
    public void getAll() {
        Set<TrackEntity> expected = Set.of(DataDummy.TRACK_4, DataDummy.TRACK_2);

        when(this.trackService.getAll()).thenReturn(expected);

        Set<TrackEntity> result = this.trackService.getAll();

        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("save should works")
    public void save() {
        this.trackService.save(DataDummy.TRACK_2);

        verify(this.trackRepositoryMock, times(1))
                .save(any(TrackEntity.class));
    }

    @Test
    @DisplayName("delete should works")
    public void delete() {
        this.trackService.delete(VALID_ID);

        verify(this.trackRepositoryMock, times(1))
                .deleteById(eq(VALID_ID));
    }
    
}
