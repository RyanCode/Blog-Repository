package com.ryan.blog.service.impl;

import com.ryan.blog.entity.User;
import com.ryan.blog.mapper.UserMapper;
import com.ryan.blog.utils.MailUtil;
import com.ryan.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void doRegister(User user) {
        userMapper.insert(user);
    }

    @Override
    public void sendVerCode(String recipient,String code) {
        MailUtil.sendRegisterMail(recipient,code);
    }



    @Override
    public Boolean identifyingCode(String code) {
        return null;
    }
}
