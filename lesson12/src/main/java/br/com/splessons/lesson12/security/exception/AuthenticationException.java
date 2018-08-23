package br.com.splessons.lesson12.security.exception;

import br.com.splessons.lesson12.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends AppException {

    private static String AUTH_USER_UNAUTHORIZED = "auth.user.unauthorized";

    public AuthenticationException() {
        super(AUTH_USER_UNAUTHORIZED, "User unauthorized");
    }

    public AuthenticationException(Throwable cause) {
        super(AUTH_USER_UNAUTHORIZED, "User unauthorized", cause);
    }
}
