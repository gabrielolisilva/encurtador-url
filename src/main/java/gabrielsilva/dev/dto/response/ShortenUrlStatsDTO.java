package gabrielsilva.dev.dto.response;

import gabrielsilva.dev.entity.ShortenUrl;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShortenUrlStatsDTO {
    private Long id;
    private String url;
    private String shortCode;
    private Integer accessCount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ShortenUrlStatsDTO(ShortenUrl entity) {
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.shortCode = entity.getShortCode();
        this.accessCount = entity.getAccessCount();
        this.created_at = entity.getCreated_at();
        this.updated_at = entity.getUpdated_at();
    }
}
