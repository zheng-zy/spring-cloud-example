package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.example.config.Const.ERROR_1001;

/**
 * <p>定制网关的熔断返回内容</p>
 * Created by zhezhiyong@163.com on 2018/2/7.
 */
@Component
public class GatewayFallbackProvider implements FallbackProvider {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MessageUtil messageUtil;

    /**
     * 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        if (cause != null && cause.getCause() != null){
            String reason = cause.getCause().getMessage();
            logger.error("exception: {}",reason);
        }
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return this.fallbackResponse();
        }
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
//                return status;
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
//                return status.value();
                return HttpStatus.OK.value();
            }
            @Override
            public String getStatusText() throws IOException {
//                return status.getReasonPhrase();
                return HttpStatus.OK.getReasonPhrase();
            }
            @Override
            public void close() {
            }
            @Override
            public InputStream getBody() throws IOException {
                JSONObject object = new JSONObject();
//                object.put("ret", status.value());
                object.put("ret", ERROR_1001);
                object.put("msg", messageUtil.getMessage(ERROR_1001));
                return new ByteArrayInputStream(object.toJSONString().getBytes());
            }
            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
