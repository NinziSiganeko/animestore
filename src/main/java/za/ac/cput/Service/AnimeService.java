package za.ac.cput.Service;

import za.ac.cput.domain.Anime;
import java.util.List;

public interface AnimeService {
    Anime create(Anime anime);
    Anime read(int animeId);
    Anime update(Anime anime);
    boolean delete(int animeId);
    List<Anime> getAll();
}
