package gabrielsilva.dev.mappers;

import gabrielsilva.dev.dto.response.ShortenUrlDTO;
import gabrielsilva.dev.dto.response.ShortenUrlStatsDTO;
import gabrielsilva.dev.entity.ShortenUrl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShortenUrlMapper {

    ShortenUrlDTO toDefaultShortenDTO(ShortenUrl shortenUrl);
    ShortenUrlStatsDTO toShortenDTOStats(ShortenUrl shortenUrl);
}
