package br.com.splessons.lesson12.payload;

public abstract class ApiResponse {

    private Boolean success;
    private String message;
    private String key;

    public ApiResponse(String key, String message, Boolean success) {
        this.key = key;
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String key, String message) {
       this(key, message, Boolean.TRUE);
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }
}
