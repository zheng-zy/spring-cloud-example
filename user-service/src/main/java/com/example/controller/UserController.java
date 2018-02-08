package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/8.
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AtomicInteger count = new AtomicInteger();

    @RequestMapping(value="/user")
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        logger.info("name: {}", name);
        int newCount = count.incrementAndGet();
        JSONObject object = new JSONObject();
        object.put("ret", 0);
        object.put("name", name);
        object.put("count", newCount);
        object.put("date", LocalTime.now());
        return object.toJSONString();
    }

}
