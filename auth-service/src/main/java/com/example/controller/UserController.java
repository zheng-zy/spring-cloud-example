package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/26.
 */
@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
}
