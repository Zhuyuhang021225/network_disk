package com.ui;


import com.ui.image.UserInforPage;
import com.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SettingPage {
JFrame          jFrame;
JPanel          jPanel;
JLabel          jlText;
JTextField      jTextField;
JButton         jButton;

    public  SettingPage(){
        jFrame = new JFrame("设置");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocation(500,200);
        jFrame.setSize(550,470);

        URL resource = UserInforPage.class.getResource("baidu_icon.png");
        Image image = new ImageIcon(resource).getImage();
        jFrame.setIconImage(image);

        jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        jPanel.setSize(550,470);
        jPanel.setLayout(null);
        jFrame.add(jPanel);

        jlText = new JLabel("下载文件位置选择：");
        jlText.setFont(new Font("微软雅黑",Font.BOLD,13));
        jlText.setBounds(110,50,140,30);
        jPanel.add(jlText);

        jTextField = new JTextField(FileUtils.DOWNLOAD_DIR);
        jTextField.setBounds(110,85,300,20);
        jTextField.setBackground(Color.white);
        jPanel.add(jTextField);

        jButton = new JButton("浏览");
        jButton.setBackground(Color.white);
        jButton.setForeground(Color.gray);
        jButton.setFont(new Font("微软雅黑",Font.PLAIN,13));
        jButton.setBounds(430,85,60,20);
        jPanel.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path="null";
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int intRetVal = jFileChooser.showOpenDialog(null);
                if( intRetVal == JFileChooser.APPROVE_OPTION){
                    path = jFileChooser.getSelectedFile().getPath();
                }
                if(path.equals("null"))
                    {
                        JOptionPane.showMessageDialog(null,"请重新选择路径");
                    }
                    else
                    {
                    FileUtils.DOWNLOAD_DIR = path;
                    JOptionPane.showMessageDialog(null,"选择路径成功");
                }
            }
        });

        jFrame.setVisible(true);
    }

}