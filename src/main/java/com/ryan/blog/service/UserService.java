package com.ryan.blog.service;

import com.ryan.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
public interface UserService extends IService<User> {
    default void doRegister(User user) {

    }

    default void sendVerCode(String recipient,String code) {

    }

    default Boolean identifyingCode(String code) {
        return null;
    }
}
