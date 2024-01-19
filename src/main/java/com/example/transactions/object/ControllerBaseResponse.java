package com.example.transactions.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ControllerBaseResponse {

    private Result result;
    private Object data;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class Result {
        private Integer resultCode;
        private String resultMessage;
    }

}
