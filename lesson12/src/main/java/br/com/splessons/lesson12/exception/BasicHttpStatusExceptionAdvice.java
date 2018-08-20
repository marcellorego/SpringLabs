package br.com.splessons.lesson12.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(HttpStatusCodeException.class)
    public ExceptionInfo handle(HttpServletRequest request, HttpStatusCodeException ex) {
        LOGGER.warn("Unexpected response from remote service. Status code: {}", ex.getStatusCode(), ex);
        String path = request.getRequestURI();
        HttpStatus status = ex.getStatusCode();
        return this.buildBasicExceptionResponse(path, status, ex);
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
        String key = ex.getKey();
        HttpStatus httpStatus = BasicHttpStatusExceptionAdvice.findResponseStatus(ex.getClass());
        ExceptionInfo exceptionInfo = this.buildAppExceptionResponse(path, key, httpStatus, ex);

        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }

    protected ExceptionInfo buildBasicExceptionResponse(String path, HttpStatus status, Exception ex) {
        return BasicHttpStatusExceptionAdvice.createExceptionInfo(path, status, ex);
    }

    protected ExceptionInfo buildAppExceptionResponse(String path, String key, HttpStatus status, Exception ex) {
        return BasicHttpStatusExceptionAdvice.createAppExceptionInfo(path, key, status, ex);
    }

    public static ExceptionInfo createAppExceptionInfo(String path, String key, HttpStatus status, Exception ex) {
        return new ExceptionInfo.ExceptionInfoBuilder()
                .path(path)
                .key(key)
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
