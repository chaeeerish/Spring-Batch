package ab180.assignments.link.exception;

import ab180.assignments.link.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LinkErrorCode implements ErrorCode {
    NOT_FOUND_LINK_BY_SHORT_ID(HttpStatus.NOT_FOUND, "LINK_001", "Short Id로 Link를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
