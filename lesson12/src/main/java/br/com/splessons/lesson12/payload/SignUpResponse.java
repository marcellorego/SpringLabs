package br.com.splessons.lesson12.payload;

public class SignUpResponse extends ApiResponse {

    public static final String SIGN_UP_SUCCESS = "sign.up.success";

    public SignUpResponse(String message) {
        super(SIGN_UP_SUCCESS, message);
    }

}
