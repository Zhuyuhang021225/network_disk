package com.ui;

import com.ui.image.UserInforPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.net.URL;
public class ClientForm extends JFrame {
JPanel            jpMenuBar;
JLabel            jlHomePage,        jlUpload,         jlTransmit,
                  jlSetting;
JLabel            jlHomePageText,    jlUploadText,     jlTransmitText,
                  jlSettingText;

public   static    JPanel            homePage,         jPanel;
public             Long              userId;
public   static    String            userName;

    public ClientForm(Long userId,String userName) {
        this.userId = userId;
        this.userName = userName;
        super.setTitle("百度网盘");
        setSize(1125, 665);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL resource = UserInforPage.class.getResource("baidu_icon.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        componentsInit();
        setVisible(true);
    }


    public  void componentsInit(){
       jpMenuBar = new JPanel();
       jpMenuBar.setBackground(Color.white);
       jpMenuBar.setLayout(null);
       jpMenuBar.setBounds(0,0,70,665);
       add(jpMenuBar);

       jPanel = new JPanel();
       jPanel.setLayout(null);
       jPanel.setBounds(71,0,1074,665);
       add(jPanel);

       homePage = new HomePage(userId, userName);
       jPanel.add(homePage);

       URL resourceHomePage = UserInforPage.class.getResource("ic_home.png");
       URL resourceHomePage2 = UserInforPage.class.getResource("ic_home2.png");
       jlHomePage = new JLabel(new ImageIcon(resourceHomePage2));
       jlHomePage.setBounds(14,15,42,42);
       jpMenuBar.add(jlHomePage);

       URL resourceTransmit = UserInforPage.class.getResource("ic_transfer.png");
       URL resourceTransmit2 = UserInforPage.class.getResource("ic_transfer2.png");
       jlTransmit = new JLabel(new ImageIcon(resourceTransmit));
       jlTransmit.setBounds(14,97,42,42);
       jpMenuBar.add(jlTransmit);

       URL resourceUpload = UserInforPage.class.getResource("ic_share.png");
       URL resourceUpload2 = UserInforPage.class.getResource("ic_share2.png");
       jlUpload = new JLabel(new ImageIcon(resourceUpload));
       jlUpload.setBounds(14,179,42,42);
       jpMenuBar.add(jlUpload);

       URL resourceSetting = UserInforPage.class.getResource("ic_more.png");
       URL resourceSetting2 = UserInforPage.class.getResource("ic_more2.png");
       jlSetting = new JLabel(new ImageIcon(resourceSetting));
       jlSetting.setBounds(14,261,42,42);
       jpMenuBar.add(jlSetting);

       jlHomePageText = new JLabel("首页");
       jlHomePageText.setFont(new Font("微软雅黑", Font.BOLD, 13));
       jlHomePageText.setForeground(Color.blue);
       jlHomePageText.setBounds(22,50,60,30);
       jpMenuBar.add(jlHomePageText);

       jlTransmitText = new JLabel("传输");
       jlTransmitText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
       jlTransmitText.setForeground(Color.gray);
       jlTransmitText.setBounds(22,132,60,30);
       jpMenuBar.add(jlTransmitText);

       jlUploadText = new JLabel("上传");
       jlUploadText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
       jlUploadText.setForeground(Color.gray);
       jlUploadText.setBounds(22,214,60,30);
       jpMenuBar.add(jlUploadText);

       jlSettingText = new JLabel("设置");
       jlSettingText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
       jlSettingText.setForeground(Color.gray);
       jlSettingText.setBounds(22,296,60,30);
       jpMenuBar.add(jlSettingText);

       jlHomePage.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               jlHomePage.setIcon(new ImageIcon(resourceHomePage2));
               jlHomePage.updateUI();
               jlTransmit.setIcon(new ImageIcon(resourceTransmit));
               jlTransmit.updateUI();
               jlUpload.setIcon(new ImageIcon(resourceUpload));
               jlUpload.updateUI();
               jlSetting.setIcon(new ImageIcon(resourceSetting));
               jlSetting.updateUI();

               jlHomePageText.setFont(new Font("微软雅黑", Font.BOLD, 13));
               jlHomePageText.setForeground(Color.blue);
               jlHomePageText.updateUI();
               jlTransmitText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlTransmitText.setForeground(Color.gray);
               jlTransmitText.updateUI();
               jlUploadText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlUploadText.setForeground(Color.gray);
               jlUploadText.updateUI();
               jlSettingText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlSettingText.setForeground(Color.gray);
               jlSettingText.updateUI();

               jPanel.removeAll();
               homePage = new HomePage(userId, userName);
               jPanel.add(homePage);
               jPanel.updateUI();
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
       jlUpload.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               jlHomePage.setIcon(new ImageIcon(resourceHomePage));
               jlHomePage.updateUI();
               jlTransmit.setIcon(new ImageIcon(resourceTransmit));
               jlTransmit.updateUI();
               jlUpload.setIcon(new ImageIcon(resourceUpload2));
               jlUpload.updateUI();
               jlSetting.setIcon(new ImageIcon(resourceSetting));
               jlSetting.updateUI();

               jlHomePageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlHomePageText.setForeground(Color.gray);
               jlHomePageText.updateUI();
               jlTransmitText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlTransmitText.setForeground(Color.gray);
               jlTransmitText.updateUI();
               jlUploadText.setFont(new Font("微软雅黑", Font.BOLD, 13));
               jlUploadText.setForeground(Color.blue);
               jlUploadText.updateUI();
               jlSettingText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlSettingText.setForeground(Color.gray);
               jlSettingText.updateUI();

               UploadPage uploadPage = new UploadPage(userId);
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
       jlTransmit.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               jlHomePage.setIcon(new ImageIcon(resourceHomePage));
               jlHomePage.updateUI();
               jlTransmit.setIcon(new ImageIcon(resourceTransmit2));
               jlTransmit.updateUI();
               jlUpload.setIcon(new ImageIcon(resourceUpload));
               jlUpload.updateUI();
               jlSetting.setIcon(new ImageIcon(resourceSetting));
               jlSetting.updateUI();

               jlHomePageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlHomePageText.setForeground(Color.gray);
               jlHomePageText.updateUI();
               jlTransmitText.setFont(new Font("微软雅黑", Font.BOLD, 13));
               jlTransmitText.setForeground(Color.blue);
               jlTransmitText.updateUI();
               jlUploadText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlUploadText.setForeground(Color.gray);
               jlUploadText.updateUI();
               jlSettingText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlSettingText.setForeground(Color.gray);
               jlSettingText.updateUI();

               jPanel.removeAll();
               TransmitPage downloadPage= new TransmitPage(userId, userName);
               jPanel.add(downloadPage);
               jPanel.updateUI();
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
       jlSetting.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               jlHomePage.setIcon(new ImageIcon(resourceHomePage));
               jlHomePage.updateUI();
               jlTransmit.setIcon(new ImageIcon(resourceTransmit));
               jlTransmit.updateUI();
               jlUpload.setIcon(new ImageIcon(resourceUpload));
               jlUpload.updateUI();
               jlSetting.setIcon(new ImageIcon(resourceSetting2));
               jlSetting.updateUI();

               jlHomePageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlHomePageText.setForeground(Color.gray);
               jlHomePageText.updateUI();
               jlTransmitText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlTransmitText.setForeground(Color.gray);
               jlTransmitText.updateUI();
               jlUploadText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
               jlUploadText.setForeground(Color.gray);
               jlUploadText.updateUI();
               jlSettingText.setFont(new Font("微软雅黑", Font.BOLD, 13));
               jlSettingText.setForeground(Color.blue);
               jlSettingText.updateUI();

              SettingPage settingPage = new SettingPage();
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
