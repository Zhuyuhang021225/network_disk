package com.ui;

import com.mysql.cj.x.protobuf.MysqlxResultset;
import com.pojo.User;
import com.ui.image.UserInforPage;
import com.utils.NetworkUtils;
import com.utils.SendEmail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.List;

public class LoginForm extends JFrame {
    JComboBox       jComboBox;
    JPanel          jPanel,                 jPanel1;
    JTextField      jtPerson,               jtPasswd;
    JCheckBox       jcbRemember,            jcbAutoLogin;
    JButton         jbLogin,                jbRefreshCode;
    JLabel          jlLogin_Logo,           jlLogin,            jlPerson,
                    jlPasswd,               jlMessageLogin,     jlForgetPasswd,
                    jlqrCode,               jlScanLogin,        jLabel1,
                    jLabel2,                jLabel3,            jlCode,
                    jlRegister,             jlError,            jlNameError,
                    jlPassWordError,        jlExit;
    JLabel          jlLogin1,               jlEmail,            jlCode1,
                    jlAccountLogin;
    JTextField      jtEmail,                jtCode;
    JButton         jbLogin1,               jbCode;
    public static   Long       code;
    public static   JFrame     loginForm;
    static          JFrame     registerForm;
    public static   JFrame     clientForm;

    public LoginForm()
    {
        super.setTitle("欢迎使用百度网盘");
        setSize(670,462);

        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        jPanel.setLayout(null);
        add(jPanel);

        jPanel1 =new JPanel();
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBounds(350,100,300,280);
        jPanel1.setLayout(null);
        jPanel.add(jPanel1);

        URL resource = UserInforPage.class.getResource("baidu_icon.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        componentsInit();
        setVisible(true);
    }

    public void componentsInit()
    {
        URL resource = UserInforPage.class.getResource("login.png");
        jlLogin_Logo =new JLabel(new ImageIcon(resource));
        jlLogin_Logo.setBounds(-5,-5,685,100);
        jPanel.add(jlLogin_Logo);

        jlLogin = new JLabel("账号密码登录");
        jlLogin.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        jlLogin.setBounds(0,0,120,40);
        jPanel1.add(jlLogin);

        jlLogin1 = new JLabel("邮箱快捷登录");
        jlLogin1.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        jlLogin1.setBounds(0,0,120,40);


        jtPerson = new JTextField("        请输入用户名");
        jtPerson.setBounds(0,60,270,42);
        jtPerson.setForeground(Color.gray);
        jtPerson.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jPanel1.add(jtPerson);

        jtPerson.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtPerson.setText("        ");
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

        jtEmail = new JTextField("        请输入QQ邮箱");
        jtEmail.setBounds(0,60,290,42);
        jtEmail.setForeground(Color.gray);
        jtEmail.setFont(new Font("微软雅黑",Font.PLAIN,16));

        URL resourceEmail = UserInforPage.class.getResource("phone.png");
        jlEmail = new JLabel(new ImageIcon(resourceEmail));
        jlEmail.setBounds(5,10,18,18);
        jtEmail.add(jlEmail);

        jtEmail.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtEmail.setText("         ");
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

        jComboBox = new JComboBox<String>();
        jComboBox.setBackground(Color.white);
        jtPerson.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jComboBox.setBounds(0,60,290,42);
        jPanel1.add(jComboBox);

        List<User> allUsers = NetworkUtils.getAllUsers();
        for (int i=0;i<allUsers.size();i++){
            jComboBox.addItem("        "+allUsers.get(i).getUsername());
            jComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    jtPerson.setText("        "+allUsers.get(jComboBox.getSelectedIndex()).getUsername());
                    jtPasswd.setText("        "+allUsers.get(jComboBox.getSelectedIndex()).getPassword());
                }
            });
        }

        URL resourcePerson = UserInforPage.class.getResource("person.png");
        jlPerson = new JLabel(new ImageIcon(resourcePerson));
        jlPerson.setBounds(5,10,18,18);
        jtPerson.add(jlPerson);

        JLabel jlPerson = new JLabel(new ImageIcon(resourcePerson));
        jlPerson.setBounds(5,10,18,18);
        jComboBox.add(jlPerson);

        jtPasswd = new JTextField("        请输入密码");
        jtPasswd.setBounds(0,120,290,42);
        jtPasswd.setForeground(Color.gray);
        jtPasswd.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jPanel1.add(jtPasswd);

        jtPasswd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtPasswd.setText("        ");
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

        URL resourcePasswd = UserInforPage.class.getResource("passwd.png");
        jlPasswd = new JLabel(new ImageIcon(resourcePasswd));
        jlPasswd.setBounds(5,10,18,18);
        jtPasswd.add(jlPasswd);

        jtCode = new JTextField("        输入动态密码");
        jtCode.setBounds(0,120,155,42);
        jtCode.setForeground(Color.gray);
        jtCode.setFont(new Font("微软雅黑",Font.PLAIN,16));

        jtCode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtCode.setText("        ");
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

        URL resourceCode1 = UserInforPage.class.getResource("shortmessage.png");
        jlCode1 = new JLabel(new ImageIcon(resourceCode1));
        jlCode1.setBounds(5,10,18,18);
        jtCode.add(jlCode1);

        jbCode = new JButton("获取动态密码");
        jbCode.setBackground(Color.WHITE);
        jbCode.setForeground(Color.blue);
        jbCode.setFont(new Font("微软雅黑",Font.PLAIN,13));
        jbCode.setBounds(170,120,120,42);

        jbCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    code = SendEmail.main(jtEmail.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jcbRemember = new JCheckBox("记住密码",true);
        jcbRemember.setBackground(Color.white);
        jcbRemember.setFont(new Font("微软雅黑",Font.PLAIN,12));
        jcbRemember.setBounds(0,170,100,40);
        jPanel1.add(jcbRemember);

        jcbAutoLogin = new JCheckBox("自动登录");
        jcbAutoLogin.setBackground(Color.white);
        jcbAutoLogin.setFont(new Font("微软雅黑",Font.PLAIN,12));
        jcbAutoLogin.setBounds(100,170,100,40);
        jPanel1.add(jcbAutoLogin);

        jbLogin = new JButton("登录");
        jbLogin.setForeground(Color.white);
        jbLogin.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jbLogin.setBounds(0,220,290,40);
        jbLogin.setBackground(Color.blue);
        jPanel1.add(jbLogin);

        jbLogin1 = new JButton("登录");
        jbLogin1.setForeground(Color.white);
        jbLogin1.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jbLogin1.setBounds(0,200,290,40);
        jbLogin1.setBackground(Color.blue);

        jbLogin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users = NetworkUtils.LoginByEmail(jtEmail.getText().trim());
                if (users!=null&&jtCode.getText().trim().equals(code.toString())){
                    clientForm = new ClientForm(Long.valueOf(users.get(0).getId()) ,users.get(0).getUsername());
                    loginForm.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"该账号可能未注册！");
                }
            }
        });

        jlMessageLogin = new JLabel("邮箱快捷登录>");
        jlMessageLogin.setForeground(Color.blue);
        jlMessageLogin.setFont(new Font("微软雅黑",Font.PLAIN,14));
        jlMessageLogin.setBounds(190,0,120,40);
        jPanel1.add(jlMessageLogin);

        jlMessageLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel1.removeAll();
                jPanel1.add(jlLogin1);
                jPanel1.add(jtEmail);
                jPanel1.add(jtCode);
                jPanel1.add(jbCode);
                jPanel1.add(jbLogin1);
                jPanel1.add(jlAccountLogin);
                jPanel1.updateUI();
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

        jlAccountLogin = new JLabel("账号密码登录>");
        jlAccountLogin.setForeground(Color.blue);
        jlAccountLogin.setFont(new Font("微软雅黑",Font.PLAIN,14));
        jlAccountLogin.setBounds(190,0,120,40);

        jlAccountLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel1.removeAll();
                jPanel1.add(jlLogin);
                jPanel1.add(jtPerson);
                jPanel1.add(jComboBox);
                jPanel1.add(jtPasswd);
                jPanel1.add(jcbRemember);
                jPanel1.add(jcbAutoLogin);
                jPanel1.add(jlMessageLogin);
                jPanel1.add(jlForgetPasswd);
                jPanel1.add(jbLogin);
                jPanel1.updateUI();
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

        jlForgetPasswd = new JLabel("忘记密码?");
        jlForgetPasswd.setForeground(Color.blue);
        jlForgetPasswd.setFont(new Font("微软雅黑",Font.PLAIN,12));
        jlForgetPasswd.setBounds(235,170,100,40);
        jPanel1.add(jlForgetPasswd);

        URL resourceqrCode = UserInforPage.class.getResource("qrcode.png");
        jlqrCode = new JLabel(new ImageIcon(resourceqrCode));
        jlqrCode.setBounds(105,115,13,13);
        jPanel.add(jlqrCode);

        jlScanLogin = new JLabel("扫一扫登录");
        jlScanLogin.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        jlScanLogin.setBounds(120,100,120,40);
        jPanel.add(jlScanLogin);

        jLabel1 =new JLabel("请使用");
        jLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jLabel1.setBounds(60,140,60,40);
        jPanel.add(jLabel1);

        jLabel2 =new JLabel("百度网盘App");
        jLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jLabel2.setForeground(Color.blue);
        jLabel2.setBounds(105,140,100,40);
        jPanel.add(jLabel2);

        jLabel3 =new JLabel("扫码登录");
        jLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jLabel3.setBounds(195,140,60,40);
        jPanel.add(jLabel3);

        URL resourceCode = UserInforPage.class.getResource("code.png");
        jlCode = new JLabel(new ImageIcon(resourceCode));
        jlCode.setBounds(72,180,180,180);
        jPanel.add(jlCode);

        jbRefreshCode = new JButton("刷新二维码");
        jbRefreshCode.setBorderPainted(false);
        jbRefreshCode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jbRefreshCode.setForeground(Color.blue);
        jbRefreshCode.setBackground(Color.white);
        jbRefreshCode.setBounds(110,370,100,30);
        jPanel.add(jbRefreshCode);

        jlRegister =new JLabel("注册账号");
        jlRegister.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jlRegister.setForeground(Color.blue);
        jlRegister.setBounds(350,390,60,40);
        jPanel.add(jlRegister);

        jlRegister.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerForm = new RegisterForm();
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

        jlExit = new JLabel("残忍退出");
        jlExit.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jlExit.setForeground(Color.gray);
        jlExit.setBounds(575,395,70,30);
        jPanel.add(jlExit);

        jlExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginForm.dispose();
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

        URL resourceError = UserInforPage.class.getResource("icon_error_tip.png");
        jlError = new JLabel(new ImageIcon(resourceError));
        jlError.setBounds(350,135,16,16);

        jlNameError =new JLabel("请输入百度账号");
        jlNameError.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlNameError.setForeground(Color.RED);
        jlNameError.setBounds(370,122,100,40);

        jlPassWordError =new JLabel("请输入密码");
        jlPassWordError.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlPassWordError.setForeground(Color.RED);
        jlPassWordError.setBounds(370,122,100,40);


        jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtPerson.getText().trim();
                String password = jtPasswd.getText().trim();

                if(username.isEmpty())
                {
                    jPanel.add(jlError);
                    jPanel.add(jlNameError);
                    setVisible(false);
                    setVisible(true);
                    jtPasswd.setText("");
                    return;
                }
                if(password.isEmpty())
                {
                    jPanel.add(jlError);
                    jPanel.add(jlPassWordError);
                    setVisible(false);
                    setVisible(true);
                    jtPerson.setText("");
                    return;
                }

                Long userId = NetworkUtils.login(username, password);
                if (userId == null||userId == -1){
                    JOptionPane.showMessageDialog(null,"登陆失败");
                    return;
                }
                clientForm = new ClientForm(userId ,username);
                loginForm.dispose();
            }
        });
    }

    public static void main(String[] args) {
        loginForm = new LoginForm();
    }
}
