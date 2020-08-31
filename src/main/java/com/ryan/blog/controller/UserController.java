package com.ryan.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.User;
import com.ryan.blog.mapper.UserMapper;
import com.ryan.blog.service.UserService;
import com.ryan.blog.utils.MathematicsUtil;
import com.ryan.blog.utils.RSAUtil;
import com.ryan.blog.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST,value = "/register/sendVerCode")
    @ResponseBody
    public ResponseDTO doRegister(@RequestBody User user){
        String recipient = user.getEmail();
        System.out.println(recipient);
        String code= MathematicsUtil.getUUID().substring(3,7);
        redisUtil.set("code", code);
        redisUtil.expire("code",50);
        userService.sendVerCode(recipient,code);
        return ResponseDTO.getInstance().suc("邮件已发送。");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/register/doRegister")
    @ResponseBody
    public ResponseDTO Register(@RequestBody Map<String, Object> map){
        System.out.println(map.get("user"));
        System.out.println(map.get("verCode"));
        LinkedHashMap<String,String> userHash = (LinkedHashMap) map.get("user");
        User user=new User();
        Random random=new Random();
        int i = random.nextInt(5)+1;
        user.setEmail(userHash.get("email"))
                .setPassword(userHash.get("password"))
                .setUsername(userHash.get("username"))
                .setCreated(new Date())
                .setAvatar("http://www.ryanmoon.cool/banner/"+i+".jpg")
                .setStatus(1)
                .setPerms(null)
                .setLastLogin(new Date());
        if (!redisUtil.get("code").equals("")){
            userService.doRegister(user);
            return ResponseDTO.getInstance().suc("注册成功！");
        }
        return ResponseDTO.getInstance().err("注册失败");
    }
    @RequestMapping(method = RequestMethod.POST,value = "/toMyPage")
    @ResponseBody
    public ResponseDTO login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str=token.getUsername() +" "+sdf.format(new Date().getTime());
            String publicKey = RSAUtil.generateKeyPair().get("publicKey");
            String signature= RSAUtil.encrypt(str,publicKey);
            redisUtil.set(signature,"token");
            redisUtil.expire(signature,1800);
            return ResponseDTO.getInstance().suc(signature);
        }catch (UnknownAccountException e){
            return ResponseDTO.getInstance().err("账号不存在！");
        }catch (IncorrectCredentialsException e){
            return ResponseDTO.getInstance().err("密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/verToken")
    @ResponseBody
    public ResponseDTO verToken(){
        String postToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(postToken);
        try{
            String getToken=String.valueOf(redisUtil.get(postToken));
            System.out.println("getToken长"+getToken.length());
            System.out.println(getToken);
            if (!getToken.equals("null")){
                System.out.println("返回true");
                return ResponseDTO.getInstance().suc(true);
            }
            return ResponseDTO.getInstance().err("请登录");
        }catch (Exception e){
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.GET,value = "/user/info")
    @ResponseBody
    public ResponseDTO getUser(@RequestParam String userName){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        List<User> user = userMapper.selectList(queryWrapper);
        return ResponseDTO.getInstance().suc(user);
    }

}