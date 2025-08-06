package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Service.AnimeService;
import za.ac.cput.domain.Anime;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
@CrossOrigin(origins = "http://localhost:3000") // React frontend
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @PostMapping
    public Anime create(@RequestBody Anime anime) {
        return animeService.update(anime);
    }

    @GetMapping
    public List<Anime> getAll() {
        return animeService.getAll();
    }

    @GetMapping("/{id}")
    public Anime getById(@PathVariable String id) {
        return animeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        animeService.delete(Integer.parseInt(id));
    }
}
