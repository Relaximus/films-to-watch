package com.ftwatch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * Created by Nobody on 21.04.2017.
 */
@RestController
@RequestMapping("/user")
public class PrincipalController {

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }
}
