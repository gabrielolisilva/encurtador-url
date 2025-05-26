package gabrielsilva.dev.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateShortenUrlRequestDTO(
        @NotBlank(message = "Campo url é obrigatório")
        String url
) {
}
