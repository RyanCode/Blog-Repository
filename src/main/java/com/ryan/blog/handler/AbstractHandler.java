package com.ryan.blog.handler;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author Ryan
 * @Date 2020/8/6 17:28
 * version 1.0
 */
public abstract class AbstractHandler implements InitializingBean {
    public void AAA(String name) {
        throw new UnsupportedOperationException();
    }

    public void BBB(String name) {
        throw new UnsupportedOperationException();
    }
}
