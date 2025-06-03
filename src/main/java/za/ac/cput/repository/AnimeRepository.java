package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Anime;
import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    List<Anime> findByGenre(String genre);
    List<Anime> findByTitleContainingIgnoreCase(String keyword);
    List<Anime> findByReleaseYearGreaterThan(int year);
}