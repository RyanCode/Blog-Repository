package com.ryan.blog.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.blog.entity.User;
import com.ryan.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Ryan
 * @Date 2020/7/20 18:12
 * version 1.0
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //拿到当前对象
        Subject subject= SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,token.getUsername());
        User user=userService.getOne(queryWrapper);
//        System.out.println(user);
        if (user == null){
            return null;//抛出unKnowAccountException
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}
