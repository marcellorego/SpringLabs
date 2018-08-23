package br.com.splessons.lesson12.controller;

import br.com.splessons.lesson12.payload.*;
import br.com.splessons.lesson12.security.JwtTokenProvider;
import br.com.splessons.lesson12.security.domain.CurrentUser;
import br.com.splessons.lesson12.security.domain.UserInfo;
import br.com.splessons.lesson12.security.helper.TokenHelper;
import br.com.splessons.lesson12.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "${app.base-path}${app.auth-path}",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private IAuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authService.signInRequest(loginRequest);

        String jwt = tokenProvider.createAuthenticationToken(authentication);

        return ResponseEntity
                .ok()
                .header(TokenHelper.TOKEN_HEADER_PREFIX, TokenHelper.TOKEN_HEADER_PREFIX + jwt)
                .body(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                          @CurrentUser UserInfo user) {

        UserInfo userInfo = authService.signUpUser(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(userInfo.getUsername()).toUri();

        return ResponseEntity.created(location).body(new SignUpResponse("User registered successfully"));
    }
}
