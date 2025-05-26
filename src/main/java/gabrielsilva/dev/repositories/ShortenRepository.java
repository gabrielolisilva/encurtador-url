package gabrielsilva.dev.repositories;

import gabrielsilva.dev.entity.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenRepository extends JpaRepository<ShortenUrl, Long> {
    Optional<ShortenUrl> findByShortCode(String shortCode);
}
