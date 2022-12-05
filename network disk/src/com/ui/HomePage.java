package com.ui;

import com.pojo.FileItem;
import com.ui.image.UserInforPage;
import com.utils.FileTextUtils;
import com.utils.FileTypeUtils;
import com.utils.NetworkUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;



public class HomePage extends JPanel {
    JPanel          jpMenuBar,          jPanel,             jpChoose,
                    jpChoose1;
    JLabel          jlHead,             jlSearch,           jlUserName;
    JButton         jbMyFile,           jbRecent,           jbImage,
                    jbVedio,            jbDocument,         jbMusic,
                    jbRest;

    JLabel          jlMyFile,          jlRecent,            jlImage,
                    jlVedio,           jlDocument,          jlMusic,
                    jlRest;

    JLabel          jlMyFileText,      jlRecentText,        jlImageText,
                    jlVedioText,       jlDocumentText,      jlMusicText,
                    jlRestText;
    static int     fileCount;
    static String  ImgType = "ImgType.png";
    static String  MusicType = "MusicType.png";
    static String  PdfType = "PdfType.png";
    static String  RarType = "RarType.png";
    static String  TxtType = "TxtType.png";
    static String  VideoType = "VideoType.png";
    static String  OtherType = "OtherType.png";
    static String  PptType = "PptType.png";

    public static  JPanel         jPanel1;
    public static  JPanel         jPanel2;
    public static  JLabel         jLabel,      jLabel1;
    public static  Long           userId;
    public static  String         userName;
    public static  JTextField     jTextField;
    public static  UserInforPage  userInforPage;

    public HomePage(Long userId,String userName)
    {
        this.userName = userName;
        this.userId = userId;
        setBounds(0,0,1055,665);
        setLayout(null);

        jpMenuBar = new JPanel();
        jpMenuBar.setLayout(null);
        jpMenuBar.setBackground(Color.WHITE);
        jpMenuBar.setBounds(0,0,160,665);
        add(jpMenuBar);

        jPanel = new JPanel();
        jPanel.setBounds(161,0,895,665);
        jPanel.setLayout(null);
        add(jPanel);

        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.white);
        jPanel1.setLayout(null);
        jPanel1.setBounds(0,0,1000,50);
        jPanel.add(jPanel1);

        jPanel2 = new JPanel();
        jPanel2.setBackground(Color.white);
        jPanel2.setBounds(0,51,1000,700);
        jPanel2.setLayout(null);
        jPanel.add(jPanel2);

        URL resource = UserInforPage.class.getResource("upload_hover.png");
        jLabel = new JLabel(new ImageIcon(resource));
        jLabel.setBounds(0,0,84,84);

        URL resource1 = UserInforPage.class.getResource("upload_normal.png");
        jLabel1 = new JLabel(new ImageIcon(resource1));
        jLabel1.setBounds(0,0,84,84);

        jlHead = new JLabel();
        URL resourceHead = UserInforPage.class.getResource("head.png");
        jlHead = new JLabel(new ImageIcon(resourceHead));
        jlHead.setBounds(15,0,38,38);
        jPanel1.add(jlHead);

        jlHead.addMouseListener(new MouseListener() {
            int count = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (count%2==0){
                    userInforPage = new UserInforPage(userId,fileCount);
                    count++;
                }else{
                    userInforPage.dispose();
                    count++;
                }
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

        jlUserName = new JLabel(userName);
        jlUserName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlUserName.setBounds(70,7,120,30);
        jPanel1.add(jlUserName);

        refreshFileList(NetworkUtils.getFileItems(userId));

        jTextField = new JTextField();
        jTextField.setText("搜索我的网盘");
        jTextField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jTextField.setForeground(Color.gray);
        jTextField.setBackground(Color.white);
        jTextField.setBounds(660,15,150,25);
        jPanel1.add(jTextField);

        jTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTextField.setText("");
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

        URL resourceSearch = UserInforPage.class.getResource("search.png");
        URL resourceSearch2 = UserInforPage.class.getResource("search2.png");
        jlSearch = new JLabel(new ImageIcon(resourceSearch));
        jlSearch.setBounds(820,20,16,16);
        jPanel1.add(jlSearch);

        jlSearch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlSearch.setIcon(new ImageIcon(resourceSearch2));
                jlSearch.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlSearch.setIcon(new ImageIcon(resourceSearch));
                jlSearch.updateUI();
            }
        });

        jbMyFile = new JButton();
        jbMyFile.setBounds(0,5,160,35);
        jbMyFile.setBorderPainted(false);
        jbMyFile.setBackground(Color.white);
        jbMyFile.setLayout(null);
        jpMenuBar.add(jbMyFile);

        jlMyFileText = new JLabel("我的文件");
        jlMyFileText.setFont(new Font("微软雅黑", Font.BOLD, 12));
        jlMyFileText.setForeground(Color.blue);
        jlMyFileText.setBounds(34,3,60,30);
        jbMyFile.add(jlMyFileText);

        jpChoose = new JPanel();
        jpChoose.setBounds(0,0,5,35);
        jpChoose.setBackground(Color.blue);
        jbMyFile.add(jpChoose);

        jpChoose1 = new JPanel();
        jpChoose1.setBounds(0,0,5,35);
        jpChoose1.setBackground(Color.white);

        URL resourceMyFile = UserInforPage.class.getResource("btn_moreoperation.png");
        URL resourceMyFile2 = UserInforPage.class.getResource("btn_moreoperation2.png");
        jlMyFile = new JLabel(new ImageIcon(resourceMyFile2));
        jlMyFile.setBounds(120,10,14,14);
        jbMyFile.add(jlMyFile);

        jbRecent = new JButton();
        jbRecent.setBounds(0,40,160,35);
        jbRecent.setBorderPainted(false);
        jbRecent.setBackground(Color.white);
        jbRecent.setLayout(null);
        jpMenuBar.add(jbRecent);

        URL resourceRecent = UserInforPage.class.getResource("recent_normal.png");
        URL resourceRecent2 = UserInforPage.class.getResource("recent_hover.png");
        jlRecent = new JLabel(new ImageIcon(resourceRecent));
        jlRecent.setBounds(34,10,14,14);
        jbRecent.add(jlRecent);

        jlRecentText = new JLabel("最近");
        jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlRecentText.setBounds(60,3,60,30);
        jbRecent.add(jlRecentText);

        jbImage = new JButton();
        jbImage.setBounds(0,75,160,35);
        jbImage.setBorderPainted(false);
        jbImage.setBackground(Color.white);
        jbImage.setLayout(null);
        jpMenuBar.add(jbImage);

        URL resourceImage = UserInforPage.class.getResource("picture_normal.png");
        URL resourceImage2 = UserInforPage.class.getResource("picture_hover.png");
        jlImage = new JLabel(new ImageIcon(resourceImage));
        jlImage.setBounds(34,10,14,14);
        jbImage.add(jlImage);

        jlImageText = new JLabel("图片");
        jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlImageText.setBounds(60,3,60,30);
        jbImage.add(jlImageText);

        jbVedio = new JButton();
        jbVedio.setBounds(0,110,160,35);
        jbVedio.setBorderPainted(false);
        jbVedio.setBackground(Color.white);
        jbVedio.setLayout(null);
        jpMenuBar.add(jbVedio);

        URL resourceVedio = UserInforPage.class.getResource("video_normal.png");
        URL resourceVedio2 = UserInforPage.class.getResource("video_hover.png");
        jlVedio = new JLabel(new ImageIcon(resourceVedio));
        jlVedio.setBounds(34,10,14,14);
        jbVedio.add(jlVedio);

        jlVedioText = new JLabel("视频");
        jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlVedioText.setBounds(60,3,60,30);
        jbVedio.add(jlVedioText);

        jbDocument = new JButton();
        jbDocument.setBounds(0,145,160,35);
        jbDocument.setBorderPainted(false);
        jbDocument.setBackground(Color.white);
        jbDocument.setLayout(null);
        jpMenuBar.add(jbDocument);

        URL resourceDocument = UserInforPage.class.getResource("file_normal.png");
        URL resourceDocument2 = UserInforPage.class.getResource("file_hover.png");
        jlDocument = new JLabel(new ImageIcon(resourceDocument));
        jlDocument.setBounds(34,10,14,14);
        jbDocument.add(jlDocument);

        jlDocumentText = new JLabel("文档");
        jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlDocumentText.setBounds(60,3,60,30);
        jbDocument.add(jlDocumentText);

        jbMusic = new JButton();
        jbMusic.setBounds(0,180,160,35);
        jbMusic.setBorderPainted(false);
        jbMusic.setBackground(Color.white);
        jbMusic.setLayout(null);
        jpMenuBar.add(jbMusic);

        URL resourceMusic = UserInforPage.class.getResource("music_normal.png");
        URL resourceMusic2 = UserInforPage.class.getResource("music_hover.png");
        jlMusic = new JLabel(new ImageIcon(resourceMusic));
        jlMusic.setBounds(34,10,14,14);
        jbMusic.add(jlMusic);

        jlMusicText = new JLabel("音乐");
        jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlMusicText.setBounds(60,3,60,30);
        jbMusic.add(jlMusicText);

        jbRest = new JButton();
        jbRest.setBounds(0,220,160,35);
        jbRest.setBorderPainted(false);
        jbRest.setBackground(Color.white);
        jbRest.setLayout(null);
        jpMenuBar.add(jbRest);

        URL resourceRest = UserInforPage.class.getResource("other_normal.png");
        URL resourceRest2 = UserInforPage.class.getResource("other_hover.png");
        jlRest = new JLabel(new ImageIcon(resourceRest));
        jlRest.setBounds(34,10,14,14);
        jbRest.add(jlRest);

        jlRestText = new JLabel("其它");
        jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlRestText.setBounds(60,3,60,30);
        jbRest.add(jlRestText);

        jlSearch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add(jTextField.getText());
                refreshFileList(NetworkUtils.searchFile(filenames, userId));
                jPanel2.updateUI();
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

        jbMyFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile2));
                jlMyFileText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlMyFileText.setForeground(Color.blue);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add(".");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();
            }
        });

        jbRecent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose);
                jlRecent.setIcon(new ImageIcon(resourceRecent2));
                jlRecentText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlRecentText.setForeground(Color.blue);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add("3");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();

            }
        });
        jbImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose);
                jlImage.setIcon(new ImageIcon(resourceImage2));
                jlImageText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlImageText.setForeground(Color.blue);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                    jPanel2.removeAll();
                    List<String> filenames =new LinkedList<>();
                    filenames.add("jpg");
                    refreshFileList(NetworkUtils.searchFile(filenames,userId));
                    jPanel2.updateUI();
            }
        });
        jbVedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose);
                jlVedio.setIcon(new ImageIcon(resourceVedio2));
                jlVedioText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlVedioText.setForeground(Color.blue);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add("mp4");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();
            }
        });
        jbDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose);
                jlDocument.setIcon(new ImageIcon(resourceDocument2));
                jlDocumentText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlDocumentText.setForeground(Color.blue);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add("txt");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();
            }
        });
        jbMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose);
                jlMusic.setIcon(new ImageIcon(resourceMusic2));
                jlMusicText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlMusicText.setForeground(Color.blue);
                jbMusic.updateUI();

                jbRest.add(jpChoose1);
                jlRest.setIcon(new ImageIcon(resourceRest));
                jlRestText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRestText.setForeground(Color.BLACK);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add("flac");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();
            }
        });
        jbRest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbMyFile.add(jpChoose1);
                jlMyFile.setIcon(new ImageIcon(resourceMyFile));
                jlMyFileText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMyFileText.setForeground(Color.BLACK);
                jbMyFile.updateUI();

                jbRecent.add(jpChoose1);
                jlRecent.setIcon(new ImageIcon(resourceRecent));
                jlRecentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlRecentText.setForeground(Color.BLACK);
                jbRecent.updateUI();

                jbImage.add(jpChoose1);
                jlImage.setIcon(new ImageIcon(resourceImage));
                jlImageText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlImageText.setForeground(Color.BLACK);
                jbImage.updateUI();

                jbVedio.add(jpChoose1);
                jlVedio.setIcon(new ImageIcon(resourceVedio));
                jlVedioText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlVedioText.setForeground(Color.BLACK);
                jbVedio.updateUI();

                jbDocument.add(jpChoose1);
                jlDocument.setIcon(new ImageIcon(resourceDocument));
                jlDocumentText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDocumentText.setForeground(Color.BLACK);
                jbDocument.updateUI();

                jbMusic.add(jpChoose1);
                jlMusic.setIcon(new ImageIcon(resourceMusic));
                jlMusicText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlMusicText.setForeground(Color.BLACK);
                jbMusic.updateUI();

                jbRest.add(jpChoose);
                jlRest.setIcon(new ImageIcon(resourceRest2));
                jlRestText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlRestText.setForeground(Color.blue);
                jbRest.updateUI();

                jPanel2.removeAll();
                List<String> filenames =new LinkedList<>();
                filenames.add("ex");
                refreshFileList(NetworkUtils.searchFile(filenames,userId));
                jPanel2.updateUI();
            }
        });
    }

    public static void refreshFileList(List<FileItem> fileItems)
    {
        fileCount = fileItems.size();
        if(fileItems.size()!=0) {
            FileTypeUtils[] data = new FileTypeUtils[fileItems.size()];
            FileTextUtils[] text = new FileTextUtils[fileItems.size()];
            int x = 30, y = 0;
            for (int i = 0; i < data.length; i++) {
                FileItem fileItem = fileItems.get(i);
                if (x > 800) {
                    x = 30;
                    y = y + 120;
                }
                data[i] = new FileTypeUtils(fileType(fileItem.getName()), fileItem.getName(), userId,userName, fileItem.getCreate_time(), fileItem.getLength(), x, y);
                text[i] = new FileTextUtils(fileItem.getName(), x + 20, y + 90);
                jPanel2.add(data[i]);
                jPanel2.add(text[i]);
                x = x + 140;
                if (x > 800) {
                    x = 30;
                    y = y + 120;
                }
                if (i == data.length - 1) {
                    JButton jButton = new JButton();
                    jButton.setBounds(x, y, 120, 100);
                    jButton.setBackground(Color.white);
                    jButton.setBorderPainted(false);
                    jButton.add(jLabel1);
                    jPanel2.add(jButton);

                    jButton.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFileChooser jFileChooser = new JFileChooser();
                            File file = new File("null");
                            int opt = jFileChooser.showOpenDialog(null);
                            if (opt == JFileChooser.APPROVE_OPTION)
                                file = jFileChooser.getSelectedFile();
                            if (file.getPath().equals("null")) {
                                JOptionPane.showMessageDialog(null, "请重新选择文件");
                            } else {
                                NetworkUtils.uploadFile(file.getAbsolutePath(), userId);
                                JOptionPane.showMessageDialog(null, "上传成功");
                                jPanel2.removeAll();
                                refreshFileList(NetworkUtils.getFileItems(userId));
                                jPanel2.updateUI();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            jButton.add(jLabel1);
                            jPanel2.updateUI();
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            jButton.add(jLabel);
                            jPanel2.updateUI();
                        }
                    });

                    JLabel jlCount = new JLabel("共"+fileItems.size()+"项");
                    jlCount.setForeground(Color.gray);
                    jlCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                    jlCount.setBounds(760,530,100,40);
                    jPanel2.add(jlCount);

                    JLabel jLabel = new JLabel("上传文件");
                    jLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                    jLabel.setBounds(x + 35, y + 90, 70, 30);
                    jPanel2.add(jLabel);
                } else {
                    continue;
                }
            }
            return;
        }else{
            URL resourceEmpty = UserInforPage.class.getResource("empty.png");
            JLabel jlEmpty = new JLabel(new ImageIcon(resourceEmpty));
            jlEmpty.setBounds(320,150,181,127);
            jPanel2.add(jlEmpty);

            JLabel jlEmpty1 = new JLabel("超大空间等你来填满，快来上传吧~");
            jlEmpty1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
            jlEmpty1.setBounds(320,270,220,40);
            jPanel2.add(jlEmpty1);

            JButton jbUpload = new JButton("上传文件");
            jbUpload.setFont(new Font("微软雅黑", Font.PLAIN, 13));
            jbUpload.setBounds(360,330,100,25);
            jbUpload.setBorderPainted(true);
            jbUpload.setForeground(Color.white);
            jbUpload.setBackground(Color.blue);
            jPanel2.add(jbUpload);

            jbUpload.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jFileChooser = new JFileChooser();
                    File file = new File("null");
                    int opt = jFileChooser.showOpenDialog(null);
                    if (opt == JFileChooser.APPROVE_OPTION)
                        file = jFileChooser.getSelectedFile();
                    if (file.getPath().equals("null")) {
                        JOptionPane.showMessageDialog(null, "请重新选择文件");
                    } else {
                        NetworkUtils.uploadFile(file.getAbsolutePath(), userId);
                        JOptionPane.showMessageDialog(null, "上传成功");
                        jPanel2.removeAll();
                        refreshFileList(NetworkUtils.getFileItems(userId));
                        jPanel2.updateUI();
                    }
                }
            });
        }
    }

    public static String fileType(String fileName)
    {
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length-1;
        if (strArray[suffixIndex].toLowerCase().equals("jpg")||
            strArray[suffixIndex].toLowerCase().equals("png")||
            strArray[suffixIndex].toLowerCase().equals("gif")||
            strArray[suffixIndex].toLowerCase().equals("bmp"))
        {
            return ImgType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("mp4")||
            strArray[suffixIndex].toLowerCase().equals("avi")||
            strArray[suffixIndex].toLowerCase().equals("mov")||
            strArray[suffixIndex].toLowerCase().equals("rmvb"))
        {
            return VideoType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("txt"))
        {
            return TxtType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("pptx"))
        {
            return PptType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("pdf"))
        {
            return PdfType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("rar")||
                strArray[suffixIndex].toLowerCase().equals("zip"))
        {
            return RarType;
        }
        if (strArray[suffixIndex].toLowerCase().equals("flac")||
            strArray[suffixIndex].toLowerCase().equals("mflac")||
            strArray[suffixIndex].toLowerCase().equals("mp3")||
            strArray[suffixIndex].toLowerCase().equals("mpeg"))
        {
            return MusicType;
        }
        return OtherType;
    }
}
