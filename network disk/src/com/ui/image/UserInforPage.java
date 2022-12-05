package com.ui.image;

import com.pojo.User;
import com.ui.LoginForm;
import com.utils.NetworkUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static com.ui.LoginForm.clientForm;
import static com.ui.HomePage.userInforPage;
import static com.ui.LoginForm.loginForm;

public class UserInforPage extends JFrame {
    JLabel         jlPersonalCenter,           jlSwitchAccout;
    JPanel         jPanel;
    JLabel         jlUserName,                 jlEmail,
                   jlFileCount,                jlPassword;
    JLabel         jlUserNameText,             jlEmailText,
                   jlFileCountText,            jlPasswordText;
    public UserInforPage(Long userId,int filecount) {
        List<User> users = NetworkUtils.selectUserByUserId(userId);
        User user = users.get(0);

        setSize(300,300);
        setLocation(getToolkit().getScreenSize().width/20*5,getToolkit().getScreenSize().height/6+10);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);

        jPanel = new JPanel();
        jPanel.setBounds(2,2,296,296);
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        add(jPanel);

        jlUserName = new JLabel("用户名：");
        jlUserName.setBounds(40,40,70,30);
        jPanel.add(jlUserName);

        jlUserNameText = new JLabel(user.getUsername());
        jlUserNameText.setBounds(150,40,100,30);
        jlUserNameText.setForeground(Color.gray);
        jPanel.add(jlUserNameText);

        jlPassword = new JLabel("密码：");
        jlPassword.setBounds(40,90,70,30);
        jPanel.add(jlPassword);

        jlPasswordText = new JLabel("***********");
        jlPasswordText.setBounds(150,90,100,30);
        jlPasswordText.setForeground(Color.gray);
        jPanel.add(jlPasswordText);

        jlEmail = new JLabel("邮箱账号：");
        jlEmail.setBounds(40,140,70,30);
        jPanel.add(jlEmail);

        jlEmailText = new JLabel(user.getEmail());
        jlEmailText.setBounds(150,140,120,30);
        jlEmailText.setForeground(Color.gray);
        jPanel.add(jlEmailText);

        jlFileCount = new JLabel("网盘文件数：");
        jlFileCount.setBounds(40,190,80,30);
        jPanel.add(jlFileCount);

        jlFileCountText = new JLabel(filecount+"");
        jlFileCountText.setBounds(170,190,100,30);
        jlFileCountText.setForeground(Color.gray);
        jPanel.add(jlFileCountText);

//        jlPersonalCenter = new JLabel("个人中心");
//        jlPersonalCenter.setBounds(40,260,120,30);
//        jPanel.add(jlPersonalCenter);
//
//        jlPersonalCenter.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                jlPersonalCenter.setForeground(Color.blue);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                jlPersonalCenter.setForeground(Color.BLACK);
//            }
//        });

        jlSwitchAccout = new JLabel("切换账号");
        jlSwitchAccout.setBounds(220,260,120,30);
        jPanel.add(jlSwitchAccout);

        jlSwitchAccout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientForm.dispose();
                userInforPage.dispose();
                loginForm = new LoginForm();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlSwitchAccout.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlSwitchAccout.setForeground(Color.BLACK);
            }
        });
    }
}
