package br.com.splessons.lesson12.mapper;

import br.com.splessons.lesson12.model.User;
import br.com.splessons.lesson12.payload.UserProfile;
import br.com.splessons.lesson12.security.domain.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "id", ignore = true)
    public abstract UserProfile fromUserToUserProfile(UserInfo user);
}
