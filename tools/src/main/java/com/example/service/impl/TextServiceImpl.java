package com.example.service.impl;

import com.example.service.TextService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * <p>转码</p>
 * Created by zhezhiyong@163.com on 2018/2/28.
 */
@Service
public class TextServiceImpl implements TextService{
    @Override
    public String gbkToUtf8(String text) {
        //            return TestEncoder.getUTF8StringFromGBKString(text);
//            String str = new String(text.getBytes("UTF-8"), "ISO-8859-1");
//        return getUTF8StringFromGBKString(text);
        try {
            return new String(text.getBytes("GBK"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "gbkToUtf8 fail";
        }
    }

    @Override
    public String utf8ToGbk(String text) {
        try {
            return new String(text.getBytes("UTF-8"), "GBK");
        } catch (UnsupportedEncodingException e) {
            return "utf8ToGbk fail";
        }
    }

    public static String getUTF8StringFromGBKString(String gbkStr) {
        try {
            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalError();
        }
    }

    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }
}
