package br.com.splessons.lesson12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends AppException {

    public ServerErrorException(String key, String message) {
        super(key, message);
    }

    public ServerErrorException(String key, String message, Throwable cause) {
        super(key, message, cause);
    }
}
