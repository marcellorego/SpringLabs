package br.com.splessons.lesson12.payload;

import br.com.splessons.lesson12.security.domain.TokenType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    private TokenType tokenType = TokenType.BEARER;
}
