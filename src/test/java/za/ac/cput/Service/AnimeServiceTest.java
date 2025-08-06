package za.ac.cput.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.AnimeServiceImpl;
import za.ac.cput.domain.Anime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimeServiceTest {

    private AnimeServiceImpl animeService;

    @BeforeEach
    void setUp() {
        animeService = new AnimeServiceImpl();
    }

    @Test
    void testSaveAnime() {
        Anime anime = new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN001"))
                .setTitle("Attack on Titan")
                .setGenre("Action")
                .build();

        Anime saved = animeService.update(anime);
        assertNotNull(saved);
        assertEquals("Attack on Titan", saved.getTitle());
    }

    @Test
    void testGetAllAnime() {
        animeService.update(new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN002"))
                .setTitle("Demon Slayer")
                .setGenre("Fantasy")
                .build());

        animeService.update(new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN003"))
                .setTitle("One Piece")
                .setGenre("Adventure")
                .build());

        List<Anime> all = animeService.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void testDeleteAnime() {
        Anime anime = new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN004"))
                .setTitle("Naruto")
                .setGenre("Shonen")
                .build();

        animeService.update(anime);
        assertFalse(animeService.getAll().isEmpty());

        animeService.delete(Integer.parseInt("AN004"));
        assertTrue(animeService.getAll().isEmpty());
    }
}
