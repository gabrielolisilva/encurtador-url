package gabrielsilva.dev.dto.response;

import java.util.List;

public record CommonErrorDTO(
        String msg,
        int status,
        List<ErrorFieldDTO> errosFields
) {
}
