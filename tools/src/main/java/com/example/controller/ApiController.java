package com.example.controller;

import com.example.dto.R;
import com.example.service.TextService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/28.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private TextService textService;

    @PostMapping("/utf82gbk")
    @ResponseBody
    public R utf82gbk(@RequestBody Map<String, String> map){
        String text = map.getOrDefault("data", "");
        String result = textService.utf8ToGbk(text);
        System.out.println("utf82gbk"+Util.getEncoding(text));
        return R.ok().put("data", result);
    }

    @PostMapping("/gbk2utf8")
    @ResponseBody
    public R gbk2utf8(@RequestBody Map<String, String> map){
        String text = map.getOrDefault("data", "");
        String result = textService.gbkToUtf8(text);
        System.out.println("gbk2utf8"+Util.getEncoding(text));
        return R.ok().put("data", result);
    }

}
