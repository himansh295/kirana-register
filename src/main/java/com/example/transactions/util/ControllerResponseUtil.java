package com.example.transactions.util;

import com.example.transactions.object.ControllerBaseResponse;
import org.springframework.http.HttpStatus;

public class ControllerResponseUtil {

    private ControllerResponseUtil() {

    }

    public static ControllerBaseResponse getSuccessResponse(Object data) {
        ControllerBaseResponse controllerBaseResponse = new ControllerBaseResponse();
        ControllerBaseResponse.Result result = new ControllerBaseResponse.Result();
        result.setResultCode(HttpStatus.OK.value());
        result.setResultMessage("SUCCESS");
        controllerBaseResponse.setResult(result);
        controllerBaseResponse.setData(data);
        return controllerBaseResponse;
    }

    public static ControllerBaseResponse getFailureResponse(String message,String errorCode) {
        ControllerBaseResponse controllerBaseResponse = new ControllerBaseResponse();
        ControllerBaseResponse.Result result = new ControllerBaseResponse.Result();
        result.setResultCode(Integer.parseInt(errorCode));
        result.setResultMessage(message);
        controllerBaseResponse.setResult(result);
        return controllerBaseResponse;
    }
}
