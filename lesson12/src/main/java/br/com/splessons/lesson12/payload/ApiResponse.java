package br.com.splessons.lesson12.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class ApiResponse {

    private Boolean success;
    private String message;
    private String key;

    public ApiResponse(String key, String message) {
        this.success = true;
        this.key = key;
        this.message = message;
    }

}
