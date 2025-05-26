package gabrielsilva.dev.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "shorten_urls")
@Entity
@Data
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "shortCode", nullable = false)
    private String shortCode;

    @Column(name = "accessCount", nullable = false)
    private Integer accessCount = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public void incrementAccessCount() {
        this.accessCount = (this.accessCount == null ? 1 : this.accessCount + 1);
    }
}
