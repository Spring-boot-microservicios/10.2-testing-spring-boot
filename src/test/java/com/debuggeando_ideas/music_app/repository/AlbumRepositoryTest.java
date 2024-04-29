package com.debuggeando_ideas.music_app.repository;

import com.debuggeando_ideas.music_app.entity.AlbumEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Operaciones Fake (Simulacion proveida por spring)
public class AlbumRepositoryTest extends RepositorySpec {

    @Autowired
    private AlbumRepository albumRepository;

    private static final Long VALID_ID = 100L;
    private static final Long INVALID_ID = 900L;

    @Test
    @DisplayName("findById should works")
    public void findById() {
        // Happy
        Optional<AlbumEntity> result = this.albumRepository.findById(VALID_ID);
//        System.out.println("result ==> " + result);

        AlbumEntity albumResult = result.get();

        assertTrue(result.isPresent()); // Debe existir
        assertAll(
            () -> assertEquals("fear of the dark", albumResult.getName()),
            () -> assertEquals("iron maiden", albumResult.getAutor()),
            () -> assertEquals(280.50, albumResult.getPrice())
        );

        // Failed
        result = this.albumRepository.findById(INVALID_ID);
        assertTrue(result.isEmpty());
    }

}
