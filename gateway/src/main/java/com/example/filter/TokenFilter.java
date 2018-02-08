package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.config.MessageUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.example.config.Const.ERROR_1000;

/**
 * <p>token验证</p>
 * Created by zhezhiyong@163.com on 2018/2/6.
 */
@Component
public class TokenFilter extends ZuulFilter{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private MessageUtil messageUtil;

    @Override
    public String filterType() {
        return "pre"; //可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; //filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    //filter需要执行的具体操作
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");// 获取请求的参数

        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(200);
            JSONObject object = new JSONObject();
            object.put("ret", Integer.valueOf(ERROR_1000));
            object.put("msg", messageUtil.getMessage(ERROR_1000));
            ctx.setResponseBody(object.toJSONString());
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
