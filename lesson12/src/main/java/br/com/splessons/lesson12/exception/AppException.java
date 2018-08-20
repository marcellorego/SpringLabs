package br.com.splessons.lesson12.exception;

public abstract class AppException extends RuntimeException {

    private String key;

    public AppException(String key, String message) {
        super(message);
        this.key = key;
    }

    public AppException(String key, String message, Throwable cause) {
        super(message, cause);
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
