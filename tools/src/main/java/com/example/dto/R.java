package com.example.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**re
 * <p>统一API响应结果封装</p>
 * Created by zhezhiyong@163.com on 2017/7/5.
 */
public class R extends LinkedHashMap<String, Object> {

    public R() {
        put("ret", 0);
    }

    public static R error() {
        return error(-1, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(-1, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("ret", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
