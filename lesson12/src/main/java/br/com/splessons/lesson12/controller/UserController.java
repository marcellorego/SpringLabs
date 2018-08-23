package br.com.splessons.lesson12.controller;

import br.com.splessons.lesson12.mapper.UserMapper;
import br.com.splessons.lesson12.payload.UserProfile;
import br.com.splessons.lesson12.security.domain.CurrentUser;
import br.com.splessons.lesson12.security.domain.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${app.base-path}${app.user-path}")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        UserProfile result = userMapper.fromUserToUserProfile(currentUser);
        return result;
    }

}
