package gabrielsilva.dev.service;

import gabrielsilva.dev.dto.request.CreateShortenUrlRequestDTO;
import gabrielsilva.dev.entity.ShortenUrl;
import gabrielsilva.dev.repositories.ShortenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShortenService {

    public final ShortenRepository shortenRepository;

    public ShortenUrl createShortUrl(CreateShortenUrlRequestDTO data) {
        String shortCode = generateShortCode();
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.setUrl(data.url());
        shortenUrl.setShortCode(shortCode);

        return shortenRepository.save(shortenUrl);
    }

    private String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        int shortCodeLength = 6;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < shortCodeLength; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }

        return stringBuilder.toString();
    }

    public Optional<ShortenUrl> getUrlByShortCode(String shortCode) {
        return shortenRepository.findByShortCode(shortCode);
    }

    public void updateShortCode(String shortCode, CreateShortenUrlRequestDTO data) {
        Optional<ShortenUrl> shortCodeDB = shortenRepository.findByShortCode(shortCode);

        if (shortCodeDB.isEmpty()) {
            throw new EntityNotFoundException("Elemento não encontrado");
        }

        ShortenUrl shortenUrl = shortCodeDB.get();
        shortenUrl.setUrl(data.url());
        shortenRepository.save(shortenUrl);
    }

    public void deleteShortenUrl(String shortCode) {
        Optional<ShortenUrl> shortCodeDB = shortenRepository.findByShortCode(shortCode);

        if (shortCodeDB.isEmpty()) {
            throw new EntityNotFoundException("Elemento não encontrado");
        }

        ShortenUrl shortenUrl = shortCodeDB.get();
        shortenRepository.delete(shortenUrl);
    }
}
