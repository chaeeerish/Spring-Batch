package ab180.assignments.link.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LinkCreateRequest (
        @NotBlank(message = "url 작성은 필수입니다.")
        String url
) {
}
