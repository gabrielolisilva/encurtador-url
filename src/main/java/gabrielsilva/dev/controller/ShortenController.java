package gabrielsilva.dev.controller;

import gabrielsilva.dev.dto.request.CreateShortenUrlRequestDTO;
import gabrielsilva.dev.dto.response.ShortenUrlDTO;
import gabrielsilva.dev.dto.response.ShortenUrlStatsDTO;
import gabrielsilva.dev.entity.ShortenUrl;
import gabrielsilva.dev.mappers.ShortenUrlMapper;
import gabrielsilva.dev.repositories.ShortenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import gabrielsilva.dev.service.ShortenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/shorten")
@RestController
@RequiredArgsConstructor
public class ShortenController {

    private final ShortenService shortenService;
    private final ShortenRepository shortenRepository;
    private final ShortenUrlMapper shortenUrlMapper;

    @GetMapping("/{shortCode}")
    public ResponseEntity<ShortenUrlDTO> getUrlByShortCode(@PathVariable("shortCode") String shortCode) {
        return shortenService
                .getUrlByShortCode(shortCode)
                .map(shortenUrl -> {
                    ShortenUrlDTO shortenUrlDTO = shortenUrlMapper.toDefaultShortenDTO(shortenUrl);
                    return ResponseEntity.status(HttpStatus.OK).body(shortenUrlDTO);
                })
                .orElseThrow(() -> new EntityNotFoundException("Elemento não encontrado"));
    }

    @GetMapping("/{shortCode}/redirect")
    public ResponseEntity<Void> getUrlByShortCodeRedirect(@PathVariable("shortCode") String shortCode) {
        return shortenService
                .getUrlByShortCode(shortCode)
                .map(shortenUrl -> {
                    shortenUrl.incrementAccessCount();
                    shortenRepository.save(shortenUrl);

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(URI.create(shortenUrl.getUrl()));
                    return new ResponseEntity<Void>(httpHeaders, HttpStatus.FOUND);
                })
                .orElseThrow(() -> new EntityNotFoundException("Elemento não encontrado"));
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<ShortenUrlStatsDTO> getUrlByShortCodeStatus(@PathVariable("shortCode") String shortCode) {
        return shortenService
                .getUrlByShortCode(shortCode)
                .map(shortenUrl -> {
                    ShortenUrlStatsDTO shortenDTOStats = shortenUrlMapper.toShortenDTOStats(shortenUrl);
                    return ResponseEntity.status(HttpStatus.OK).body(shortenDTOStats);
                })
                .orElseThrow(() -> new EntityNotFoundException("Elemento não encontrado"));
    }

    @PostMapping
    public ResponseEntity<ShortenUrlDTO> createShortUrl(@RequestBody @Valid CreateShortenUrlRequestDTO data) {
        ShortenUrl shortenUrl = shortenService.createShortUrl(data);
        ShortenUrlDTO defaultShortenDTO = shortenUrlMapper.toDefaultShortenDTO(shortenUrl);
        return ResponseEntity.status(201).body(defaultShortenDTO);
    }

    @PutMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShortenUrl(
            @PathVariable("shortCode") String shortCode,
            @RequestBody CreateShortenUrlRequestDTO data
            ) {
        shortenService.updateShortCode(shortCode, data);
    }

    @DeleteMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.OK)
    public void  deleteShortenUrl(
        @PathVariable("shortCode") String shortCode
    ) {
        shortenService.deleteShortenUrl(shortCode);
    }
}
