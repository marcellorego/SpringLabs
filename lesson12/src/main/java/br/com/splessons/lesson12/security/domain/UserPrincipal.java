package br.com.splessons.lesson12.security.domain;


import br.com.splessons.lesson12.model.Role;
import br.com.splessons.lesson12.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails, UserInfo {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String username;

    private final boolean active;

    @JsonIgnore
    private final String email;

    @JsonIgnore
    private final String password;

    private final Set<Role> roles;

    private UserPrincipal(Long id, String firstName, String lastName, String username,
                         boolean active, String email, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.active = active;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrincipal create(User user) {

       return new UserPrincipal(
               user.getId(),
               user.getFirstName(),
               user.getLastName(),
               user.getUsername(),
               user.isActive(),
               user.getEmail(),
               user.getPassword(),
               user.getRoles()
       );
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
        .stream()
        .map(role -> role.getName())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}