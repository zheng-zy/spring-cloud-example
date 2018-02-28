package com.example.controller;

import com.example.service.TextService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/28.
 */
@Controller
public class IndexController {

    @Resource
    private TextService textService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

}
