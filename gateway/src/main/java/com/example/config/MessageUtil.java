package com.example.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * <p>错误获取</p>
 * Created by zhezhiyong@163.com on 2018/2/7.
 */
@Component
public class MessageUtil {

    @Resource
    private MessageSource messageSource;

    public String getMessage(String code){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }


}
