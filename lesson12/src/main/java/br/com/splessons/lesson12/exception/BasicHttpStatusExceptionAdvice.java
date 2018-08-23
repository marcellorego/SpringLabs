package br.com.splessons.lesson12.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@RestControllerAdvice
public class BasicHttpStatusExceptionAdvice extends ResponseEntityExceptionHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BasicHttpStatusExceptionAdvice.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionInfo> handle(HttpServletRequest request, Exception ex) {
        LOGGER.warn("Unexpected response from remote service. Status code: {}", HttpStatus.UNAUTHORIZED, ex);
        String path = request.getRequestURI();
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ExceptionInfo exceptionInfo = this.buildBasicExceptionResponse(path, httpStatus, ex);
        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ExceptionInfo> handle(HttpServletRequest request, HttpStatusCodeException ex) {
        LOGGER.warn("Unexpected response from remote service. Status code: {}", ex.getStatusCode(), ex);
        String path = request.getRequestURI();
        HttpStatus httpStatus = ex.getStatusCode();
        ExceptionInfo exceptionInfo = this.buildBasicExceptionResponse(path, httpStatus, ex);
        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }

//    @ExceptionHandler(AppException.class)
//    public ExceptionInfo handle(HttpServletRequest request, AppException ex) {
//        LOGGER.warn("Unexpected response from remote service. Key code: {}", ex.getKey(), ex);
//        String path = request.getRequestURI();
//        String key = ex.getKey();
//        HttpStatus httpStatus = BasicHttpStatusExceptionAdvice.findResponseStatus(ex.getClass());
//        return this.buildAppExceptionResponse(path, key, httpStatus, ex);
//    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionInfo> handleAppException(
            HttpServletRequest request, AppException ex) {

        LOGGER.warn("Unexpected response from remote service. Key code: {}", ex.getKey(), ex);
        String path = request.getRequestURI();
        HttpStatus httpStatus = BasicHttpStatusExceptionAdvice.findResponseStatus(ex.getClass());
        ExceptionInfo exceptionInfo = this.buildAppExceptionResponse(path, httpStatus, ex);

        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }

    protected ExceptionInfo buildBasicExceptionResponse(String path, HttpStatus status, Exception ex) {
        return BasicHttpStatusExceptionAdvice.createExceptionInfo(path, status, ex);
    }

    protected ExceptionInfo buildAppExceptionResponse(String path, HttpStatus status, AppException ex) {
        return BasicHttpStatusExceptionAdvice.createAppExceptionInfo(path, status, ex);
    }

    public static ExceptionInfo createAppExceptionInfo(String path, HttpStatus status, AppException ex) {
        return new ExceptionInfo.ExceptionInfoBuilder()
                .path(path)
                .key(ex.getKey())
                .status(status.toString())
                .error(status.getReasonPhrase())
                .message(ex.getLocalizedMessage())
                .exception(ex.getClass().getName())
                .cause(ex.getCause() != null ? ex.getCause().getMessage() : ex.toString())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static ExceptionInfo createExceptionInfo(String path, HttpStatus status, Exception ex) {
        return new ExceptionInfo.ExceptionInfoBuilder()
                .path(path)
                .status(status.toString())
                .error(status.getReasonPhrase())
                .message(ex.getLocalizedMessage())
                .exception(ex.getClass().getName())
                .cause(ex.getCause() != null ? ex.getCause().getMessage() : ex.toString())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    private static HttpStatus findResponseStatus(Class<? extends AppException> exceptionClass) {

        HttpStatus result = HttpStatus.INTERNAL_SERVER_ERROR;

        Annotation[] annotations = exceptionClass.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof ResponseStatus){
                ResponseStatus responseStatus = (ResponseStatus) annotation;
                result = responseStatus.value();
                break;
            }
        }

        return result;
    }
}
