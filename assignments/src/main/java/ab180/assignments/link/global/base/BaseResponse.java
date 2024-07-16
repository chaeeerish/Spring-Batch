package ab180.assignments.link.global.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"status", "errorCode", "message", "result"})
public class BaseResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(T result) {
        this.data = result;
    }
}