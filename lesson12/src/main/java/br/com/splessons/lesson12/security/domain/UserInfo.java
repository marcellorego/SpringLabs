package br.com.splessons.lesson12.security.domain;

import java.util.Set;

import br.com.splessons.lesson12.model.Role;

public interface UserInfo {

    Long getId();

    Set<Role> getRoles();

    String getName();

    String getUsername();

    String getEmail();
}
