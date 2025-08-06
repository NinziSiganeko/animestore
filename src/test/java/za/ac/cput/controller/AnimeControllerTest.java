package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Service.impl.AnimeServiceImpl;
import za.ac.cput.domain.Anime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimeControllerTest {

    private AnimeController animeController;

    @BeforeEach
    void setUp() {
        animeController = new AnimeController(new AnimeServiceImpl());
    }

    @Test
    void testCreateAnime() {
        Anime anime = new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN005"))
                .setTitle("Bleach")
                .setGenre("Shonen")
                .build();

        animeController.create(anime);
        List<Anime> all = animeController.getAll();
        assertEquals(1, all.size());
        assertEquals("Bleach", all.get(0).getTitle());
    }

    @Test
    void testGetById() {
        Anime anime = new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN006"))
                .setTitle("Fullmetal Alchemist")
                .setGenre("Adventure")
                .build();

        animeController.create(anime);
        Anime found = animeController.getById("AN006");
        assertNotNull(found);
        assertEquals("Fullmetal Alchemist", found.getTitle());
    }

    @Test
    void testDeleteAnime() {
        Anime anime = new Anime.Builder()
                .setAnimeId(Integer.parseInt("AN007"))
                .setTitle("Tokyo Ghoul")
                .setGenre("Horror")
                .build();

        animeController.create(anime);
        assertFalse(animeController.getAll().isEmpty());

        animeController.delete("AN007");
        assertTrue(animeController.getAll().isEmpty());
    }
}
