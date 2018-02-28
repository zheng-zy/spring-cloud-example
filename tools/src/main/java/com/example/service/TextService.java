package com.example.service;

/**
 * <p>文字编码转换</p>
 * Created by zhezhiyong@163.com on 2018/2/28.
 */
public interface TextService {

    String gbkToUtf8(String text);
    String utf8ToGbk(String text);

}
