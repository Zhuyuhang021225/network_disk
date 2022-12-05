package com.ui;

import com.ui.image.UserInforPage;
import com.utils.NetworkUtils;
import com.utils.SendEmail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import static com.ui.LoginForm.registerForm;

public class RegisterForm extends JFrame {
    JPanel          jPanel;
    JButton         jbGetCode,      jbRegister;
    JLabel          jlTxt,          jlTxt1,         jlTxt2,
                    jlPhone,        jlUserName,     jlPassWord,
                    jlCode;
    JTextField      jtPhone,        jtUserName,     jtPassWord,
                    jtCode;
    Long            code;

    public RegisterForm(){

        setTitle("注册百度账号");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(getToolkit().getScreenSize().width/20*13,getToolkit().getScreenSize().height/7);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setSize(485,630);

        URL resource = UserInforPage.class.getResource("baidu_icon.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        componentInit();

        setVisible(true);
    }

    private void componentInit() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);
        add(jPanel);

        jlTxt = new JLabel("欢迎注册");
        jlTxt.setFont(new Font("微软雅黑",Font.BOLD,37));
        jlTxt.setBackground(Color.white);
        jlTxt.setBounds(40,60,150,40);
        jPanel.add(jlTxt);

        jlTxt1 = new JLabel("已有帐号？");
        jlTxt1.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlTxt1.setForeground(Color.GRAY);
        jlTxt1.setBounds(40,105,100,30);
        jPanel.add(jlTxt1);

        jlTxt2 = new JLabel("登录");
        jlTxt2.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlTxt2.setForeground(Color.blue);
        jlTxt2.setBounds(120,105,30,30);
        jPanel.add(jlTxt2);

        jlPhone = new JLabel("邮   箱");
        jlPhone.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlPhone.setForeground(Color.gray);
        jlPhone.setBounds(40,200,90,30);
        jPanel.add(jlPhone);

        jtPhone = new JTextField();
        jtPhone.setBackground(Color.white);
        jtPhone.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtPhone.setBounds(100,193,330,40);
        jPanel.add(jtPhone);

        jlUserName = new JLabel("用户名");
        jlUserName.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlUserName.setForeground(Color.gray);
        jlUserName.setBounds(40,278,90,30);
        jPanel.add(jlUserName);

        jtUserName = new JTextField();
        jtUserName.setBackground(Color.white);
        jtUserName.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtUserName.setBounds(100,271,330,40);
        jPanel.add(jtUserName);

        jlPassWord = new JLabel("密   码");
        jlPassWord.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlPassWord.setForeground(Color.gray);
        jlPassWord.setBounds(40,348,90,30);
        jPanel.add(jlPassWord);

        jtPassWord = new JTextField();
        jtPassWord.setBackground(Color.white);
        jtPassWord.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtPassWord.setBounds(100,341,330,40);
        jPanel.add(jtPassWord);

        jlCode = new JLabel("验证码");
        jlCode.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlCode.setForeground(Color.gray);
        jlCode.setBounds(40,418,90,30);
        jPanel.add(jlCode);

        jtCode = new JTextField();
        jtCode.setBackground(Color.white);
        jtCode.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtCode.setBounds(100,411,190,40);
        jPanel.add(jtCode);

        jbGetCode = new JButton("获取验证码");
        jbGetCode.setBackground(Color.white);
        jbGetCode.setBounds(310,411,120,40);
        jPanel.add(jbGetCode);

        jbRegister = new JButton("注册");
        jbRegister.setFont(new Font("微软雅黑",Font.BOLD,15));
        jbRegister.setBackground(Color.blue);
        jbRegister.setForeground(Color.white);
        jbRegister.setBounds(50,511,380,50);
        jPanel.add(jbRegister);

        jbGetCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiver = jtPhone.getText();
                try {
                    code = SendEmail.main(receiver);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jbRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtCode.getText().equals(code.toString())) {
                    String result = NetworkUtils.register(System.currentTimeMillis()%10000, jtPhone.getText(),
                            jtUserName.getText(), jtPassWord.getText(), jtPhone.getText());
                    if (result.equals("注册成功")) {
                        JOptionPane.showMessageDialog(null, "你已成功注册，快去登录吧！");
                        jtPhone.setText("");
                        jtUserName.setText("");
                        jtPassWord.setText("");
                        jtCode.setText("");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null,"注册失败，稍后再试");
                jtPhone.setText("");
                jtUserName.setText("");
                jtPassWord.setText("");
                jtCode.setText("");
            }
        });

        jlTxt2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerForm.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
