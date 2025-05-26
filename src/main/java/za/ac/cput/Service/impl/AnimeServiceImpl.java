package za.ac.cput.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Anime;
import za.ac.cput.Repository.AnimeRepository;
import za.ac.cput.Service.AnimeService;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository repository;

    @Autowired
    public AnimeServiceImpl(AnimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Anime create(Anime anime) {
        return repository.save(anime);
    }

    @Override
    public Anime read(int animeId) {
        return repository.findById(animeId).orElse(null);
    }

    @Override
    public Anime update(Anime anime) {
        if (repository.existsById(anime.getAnimeId())) {
            return repository.save(anime);
        }
        return null;
    }

    @Override
    public boolean delete(int animeId) {
        if (repository.existsById(animeId)) {
            repository.deleteById(animeId);
            return true;
        }
        return false;
    }

    @Override
    public List<Anime> getAll() {
        return repository.findAll();
    }
}
