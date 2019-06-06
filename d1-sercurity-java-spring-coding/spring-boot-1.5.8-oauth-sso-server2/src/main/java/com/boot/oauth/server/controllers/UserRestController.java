package com.boot.oauth.server.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @RequestMapping(value="/user/me",method=RequestMethod.GET)
    public Principal user(Principal principal) {
        return principal;
    }
}
