package com.example.config;

import com.example.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/6.
 */
@Configuration
public class BeanConfig {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

}
