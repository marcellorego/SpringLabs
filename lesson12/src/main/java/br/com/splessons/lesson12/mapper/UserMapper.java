package br.com.splessons.lesson12.mapper;

import br.com.splessons.lesson12.model.User;
import br.com.splessons.lesson12.payload.SignUpRequest;
import br.com.splessons.lesson12.payload.UserProfile;
import br.com.splessons.lesson12.security.domain.UserInfo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    public abstract UserProfile fromUserToUserProfile(UserInfo user);


    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public abstract User fromSignUpRequestToUser(SignUpRequest signUpRequest);

    @AfterMapping
    public void fromSignUpRequestToUserAfterMapping(SignUpRequest source, @MappingTarget User target) {
        target.setPassword(passwordEncoder.encode(source.getPassword()));
    }
}
