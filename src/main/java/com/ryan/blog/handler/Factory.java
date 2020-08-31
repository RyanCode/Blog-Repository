package com.ryan.blog.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ryan
 * @Date 2020/8/6 17:28
 * version 1.0
 */
public class Factory {
    private static Map<String,AbstractHandler> strategyMap=new HashMap<>();

    public static AbstractHandler getInvokeStrategy(String str){
        return strategyMap.get(str);
    }

    public static void register(String str, AbstractHandler handler){
        if (StringUtils.isEmpty(str)||null==handler){
            return;
        }
        strategyMap.put(str,handler);
    }
}
