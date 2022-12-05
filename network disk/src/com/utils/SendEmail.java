package com.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 测试发送邮件
 */
public class SendEmail {
    public static Long main(String receiver) throws Exception {
        Properties pro = new Properties();
        pro.setProperty("mail.host","smtp.qq.com");//设置QQ邮件服务器
        pro.setProperty("mail.transport.protocol","smtp");//设置传输协议
        pro.setProperty("mail.smtp.auth","true");//需要验证用户名密码

        //Java发送邮件的5个步骤
        //1.创建应用程序所需的环境信息的Session对象
        Session session = Session.getDefaultInstance(pro, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("3380094155@qq.com","omtlabbihusbcjcb");//发件人邮箱及授权码
            }
        });
        session.setDebug(true);//开启debug模式，查看进度状态

        //2.通过session得到transport对象
        Transport ts = session.getTransport();
        //3.使用邮箱用户名及授权码连接上邮件服务器
        ts.connect("smtp.qq.com","3380094155@qq.com","omtlabbihusbcjcb");

        //4.创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //指定邮件发件人
        message.setFrom(new InternetAddress("3380094155@qq.com"));

        //指定邮件收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));

        //邮件标题
        message.setSubject("百度帐号");

        long    code = GetRandomNum(100000,999999);
        //邮件的文本内容
        message.setContent("验证码："+code+" 。您正在使用注册功能，验证码提供他人可能导致百度帐号被盗，请勿转发或泄漏。","text/html;charset=UTF-8");

        //发送邮件
        ts.sendMessage(message,message.getAllRecipients());

        ts.close();
        return code;
    }

    public static int GetRandomNum(int num1, int num2)
    {
        int result = (int) (num1 + Math.random() * (num2 - num1 + 1));
        return result;
    }

}
