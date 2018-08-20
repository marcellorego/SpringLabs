package br.com.splessons.lesson12.service.impl;

import br.com.splessons.lesson12.exception.AppException;
import br.com.splessons.lesson12.exception.BadRequestException;
import br.com.splessons.lesson12.exception.ResourceNotFoundException;
import br.com.splessons.lesson12.model.Role;
import br.com.splessons.lesson12.model.RoleName;
import br.com.splessons.lesson12.model.User;
import br.com.splessons.lesson12.payload.SignUpRequest;
import br.com.splessons.lesson12.repository.RoleRepository;
import br.com.splessons.lesson12.repository.UserRepository;
import br.com.splessons.lesson12.security.domain.UserInfo;
import br.com.splessons.lesson12.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    private void validateSignUpRequest(SignUpRequest signUpRequest) {

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
            throw new BadRequestException("auth.username.exists", "Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
            throw new BadRequestException("auth.email.exists", "Email Address already in use!");
        }
    }

    @Override
    public UserInfo signUpUser(SignUpRequest signUpRequest) throws AppException {

        validateSignUpRequest(signUpRequest);

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER.name())
                .orElseThrow(() -> new ResourceNotFoundException("auth.role.exists", "Role", "name", RoleName.ROLE_USER.name()));

        // Creating user's account
        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                Collections.singleton(userRole));

        User result = userRepository.save(user);

        return result;
    }
}
