package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>授权</p>
 * Created by zhezhiyong@163.com on 2018/2/7.
 */
@RestController
public class AuthController {

    @PostMapping("/getToken")
    @ResponseBody
    public String getToken(@RequestBody User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        return "";
    }

}
