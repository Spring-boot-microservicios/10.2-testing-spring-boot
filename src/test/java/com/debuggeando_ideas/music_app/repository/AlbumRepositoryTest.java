package com.debuggeando_ideas.music_app.repository;

import com.debuggeando_ideas.music_app.DataDummy;
import com.debuggeando_ideas.music_app.entity.AlbumEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Test
    @DisplayName("findAll should works")
    public void findAll() {
        List<AlbumEntity> result = (List<AlbumEntity>) this.albumRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("save should works")
    public void save() {
        AlbumEntity result = this.albumRepository.save(DataDummy.ALBUM);

        assertEquals(DataDummy.ALBUM, result);
    }

    @Test
    @DisplayName("findByPriceBetween should works")
    public void findByPriceBetween() {
        Set<AlbumEntity> result = this.albumRepository.findByPriceBetween(270.00, 271.00);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        // No se puede usar el verify
    }

    @Test
    @DisplayName("deleteById should works")
    public void deleteById() {
        List<AlbumEntity> records = (List<AlbumEntity>) this.albumRepository.findAll();
        int totalRecords = records.size();

        assertEquals(2, totalRecords);

        Optional<AlbumEntity> albumToDelete = this.albumRepository.findById(VALID_ID);

        // Realizamos la eliminacion
        this.albumRepository.deleteById(albumToDelete.get().getAlbumId());

        records = (List<AlbumEntity>) this.albumRepository.findAll();
        totalRecords = records.size();

        // Comprobamos que se haya eliminado
        assertEquals(1, totalRecords);
    }


}
