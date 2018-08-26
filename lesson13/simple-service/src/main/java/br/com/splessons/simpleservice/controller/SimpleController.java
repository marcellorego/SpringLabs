package br.com.splessons.simpleservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @Value("${user.environment}")
    private String userEnv;

    @GetMapping(value = "/env/{username}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String userEnv(@PathVariable("username") String username) {
        return String.format("Hello %s! You are at %s environment\n", username, userEnv);
    }
}
