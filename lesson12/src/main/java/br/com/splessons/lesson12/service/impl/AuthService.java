package br.com.splessons.lesson12.service.impl;

import br.com.splessons.lesson12.exception.AppException;
import br.com.splessons.lesson12.exception.BadRequestException;
import br.com.splessons.lesson12.exception.ResourceNotFoundException;
import br.com.splessons.lesson12.mapper.UserMapper;
import br.com.splessons.lesson12.model.Role;
import br.com.splessons.lesson12.model.RoleName;
import br.com.splessons.lesson12.model.User;
import br.com.splessons.lesson12.payload.LoginRequest;
import br.com.splessons.lesson12.payload.SignUpRequest;
import br.com.splessons.lesson12.repository.RoleRepository;
import br.com.splessons.lesson12.repository.UserRepository;
import br.com.splessons.lesson12.security.domain.UserInfo;
import br.com.splessons.lesson12.security.exception.AuthenticationException;
import br.com.splessons.lesson12.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    private void validateSignUpRequest(SignUpRequest signUpRequest) {

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("auth.username.exists", "Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("auth.email.exists", "Email Address already in use!");
        }
    }

    @Override
    public UserInfo signUpUser(SignUpRequest signUpRequest) throws AppException {

        validateSignUpRequest(signUpRequest);

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER.name())
                .orElseThrow(() -> new ResourceNotFoundException("auth.role.exists", "Role", "name", RoleName.ROLE_USER.name()));

        // Creating user's account
        User user = userMapper.fromSignUpRequestToUser(signUpRequest);
        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        return result;
    }

    public Authentication signInRequest(LoginRequest loginRequest) throws AuthenticationException {

        Objects.requireNonNull(loginRequest.getUsernameOrEmail());
        Objects.requireNonNull(loginRequest.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
            );
            return authentication;
        } catch (DisabledException | BadCredentialsException | AuthenticationException e) {
            throw new AuthenticationException(e);
        }
    }
}
