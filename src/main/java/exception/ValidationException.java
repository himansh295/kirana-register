package exception;

import lombok.Getter;

@Getter
public class ValidationException extends Exception{
    private String message;

    public ValidationException(String message) {
        super(message);
    }
}
