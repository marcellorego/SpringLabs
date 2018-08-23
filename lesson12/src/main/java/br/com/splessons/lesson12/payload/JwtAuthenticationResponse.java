package br.com.splessons.lesson12.payload;

import br.com.splessons.lesson12.security.domain.TokenType;

public class JwtAuthenticationResponse {

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    private TokenType tokenType = TokenType.BEARER;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
