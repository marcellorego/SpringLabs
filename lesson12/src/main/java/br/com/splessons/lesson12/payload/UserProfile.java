package br.com.splessons.lesson12.payload;

import br.com.splessons.lesson12.model.Role;
import br.com.splessons.lesson12.security.domain.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserProfile implements UserInfo {

    @JsonIgnore
    private Long id;

    private String name;
    private String username;
    private String email;
    private Set<Role> roles;

}
