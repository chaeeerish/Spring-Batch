package ab180.assignments.link.dto.response;

import java.time.LocalDateTime;

public record LinkResponse(
        String shortId,
        String url,
        LocalDateTime createdAt
) {
}
