package com.ryan.blog.utils;

import java.util.UUID;

/**
 * @Author Ryan
 * @Date 2020/7/22 10:09
 * version 1.0
 */
public class MathematicsUtil {
    /**
     * @return verCode
     * 生成UUID
     */
    public static String getUUID(){
        String uuiStr = UUID.randomUUID().toString().replace("-","");
        return uuiStr;
    }
}
