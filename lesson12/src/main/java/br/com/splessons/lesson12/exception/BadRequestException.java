package br.com.splessons.lesson12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AppException {

    public BadRequestException(String key, String message) {
        super(key, message);
    }

    public BadRequestException(String key, String message, Throwable cause) {
        super(key, message, cause);
    }
}
