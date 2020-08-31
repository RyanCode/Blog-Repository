package com.ryan.blog.handler;

import org.springframework.stereotype.Component;

/**
 * @Author Ryan
 * @Date 2020/8/6 17:29
 * version 1.0
 */
@Component
public class UserRoleAbstractHandler extends AbstractHandler {

    @Override
    public void AAA(String name) {
        super.AAA(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("张三",this);
    }
}
