package com.ryan.blog.utils;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * @Author Ryan
 * @Date 2020/7/21 17:36
 * version 1.0
 */
public class MailUtil {
    public static void sendRegisterMail(String recipient,String code) {
        Properties prop=new Properties();
//         设置邮件服务器主机名，这里是163
        prop.put("mail.host","smtp.163.com" );
//         发送邮件协议名称
        prop.put("mail.transport.protocol", "smtp");
//         是否认证
        prop.put("mail.smtp.auth", true);
        try{
            MailSSLSocketFactory sslSocketFactory=new MailSSLSocketFactory();
            sslSocketFactory.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sslSocketFactory);
            Session session=Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Ryancoder@163.com","BCLLVAOISZZYGEWK");
                }
            });
            session.setDebug(true);
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress("Ryancoder@163.com"));
            System.out.println("初始化mail服务器");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("RBlog激活邮件");
            String content="感谢您注册RBlog平台,您的激活码是："+code;
            message.setContent(content,"text/html;charset=UTF-8");
            Transport transport=session.getTransport();
            transport.connect();
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
