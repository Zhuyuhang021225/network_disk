package com.utils;

import com.ui.TransmitPage;
import com.ui.image.UserInforPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.utils.FileTypeUtils.fileSize;
import static com.utils.FileUtils.DOWNLOAD_DIR;
import static com.ui.ClientForm.jPanel;

public class RecordUtils extends JButton {

    JLabel          jlFileType,         jlFiletext,         jlFileSize,
                    jlOpenFile,         jlOpenFolder,       jlClear,
                    jlCreateTime,       jlUpBtn,            jlUpText,
                    jlDnBtn,            jlDnText;

    public RecordUtils(String recordType,String fileType,String fileName,Long userId,String userName,String createtime,Long length,int x,int y){
        URL resourceType = UserInforPage.class.getResource("small"+fileType);
        jlFileType = new JLabel(new ImageIcon(resourceType));
        jlFileType.setBounds(15,15,30,31);
        add(jlFileType);

        jlFiletext = new JLabel();
        jlFiletext.setText(fileName);
        jlFiletext.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlFiletext.setBounds(60,15,200,20);
        add(jlFiletext);

        jlFileSize = new JLabel();
        jlFileSize.setText(fileSize(length));
        jlFileSize.setForeground(Color.gray);
        jlFileSize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlFileSize.setBounds(60,30,200,20);
        add(jlFileSize);

        jlCreateTime = new JLabel();
        jlCreateTime.setText(createtime);
        jlCreateTime.setForeground(Color.gray);
        jlCreateTime.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlCreateTime.setBounds(310,25,200,20);
        add(jlCreateTime);

        if(recordType.equals("upload")) {
            URL resourceUpBtn = UserInforPage.class.getResource("upload2.png");
            jlUpBtn = new JLabel(new ImageIcon(resourceUpBtn));
            jlUpBtn.setBounds(500, 25, 20, 20);
            jlUpBtn.setToolTipText("上传完成");
            add(jlUpBtn);

            jlUpText = new JLabel("上传完成");
            jlUpText.setForeground(Color.gray);
            jlUpText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
            jlUpText.setBounds(520, 25, 200, 20);
            add(jlUpText);
        }if (recordType.equals("download")){
            URL resourceDnBtn = UserInforPage.class.getResource("download2.png");
            jlDnBtn = new JLabel(new ImageIcon(resourceDnBtn));
            jlDnBtn.setBounds(500, 25, 20, 20);
            jlDnBtn.setToolTipText("下载完成");
            add(jlDnBtn);

            jlDnText = new JLabel("下载完成");
            jlDnText.setForeground(Color.gray);
            jlDnText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
            jlDnText.setBounds(520, 25, 200, 20);
            add(jlDnText);
        }

        URL resourceOpenFile = UserInforPage.class.getResource("create_online_doc.png");
        URL resourceOpenFile1 = UserInforPage.class.getResource("create_online_doc1.png");
        jlOpenFile = new JLabel(new ImageIcon(resourceOpenFile));
        jlOpenFile.setBounds(660,25,20,20);
        jlOpenFile.setToolTipText("打开文件");
        add(jlOpenFile);

        URL resourceOpenFolder = UserInforPage.class.getResource("openfolder.png");
        URL resourceOpenFolder1 = UserInforPage.class.getResource("openfolder1.png");
        jlOpenFolder = new JLabel(new ImageIcon(resourceOpenFolder));
        jlOpenFolder.setBounds(730,25,20,20);
        jlOpenFolder.setToolTipText("打开所在文件夹");
        add(jlOpenFolder);

        URL resourceClear = UserInforPage.class.getResource("clear.png");
        URL resourceClear1 = UserInforPage.class.getResource("clear1.png");
        jlClear = new JLabel(new ImageIcon(resourceClear));
        jlClear.setBounds(800,25,20,20);
        jlClear.setToolTipText("清除记录");
        add(jlClear);

        setName(fileName);
//        setToolTipText("<html><body>"+"名称："+fileName+"<br>"+"修改时间："+createtime+"<br>"+"文件大小："+fileSize(length)+"<body></html>");
        setBounds(x,y,1000,60);
        setLayout(null);
        setBackground(Color.white);
        setBorderPainted(false);

        addMouseListener(new MouseListener() {
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
                setBackground(Color.getHSBColor(400,500,200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });

        jlOpenFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file = new File("D:\\Client\\temp");
                if (!file.exists()){
                    file.mkdirs();
                }
                NetworkUtils.downLoadFile(fileName,userName,userId,"temp");
                try {
                    Desktop.getDesktop().open(new File( "D:\\Client\\temp\\" + fileName ));
                } catch (IOException ex) {
                    ex.printStackTrace();
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
                jlOpenFile.setIcon(new ImageIcon(resourceOpenFile1));
                jlOpenFile.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlOpenFile.setIcon(new ImageIcon(resourceOpenFile));
                jlOpenFile.updateUI();
            }
        });

        jlOpenFolder.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new File(DOWNLOAD_DIR));
                } catch (IOException ex) {
                    ex.printStackTrace();
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
                jlOpenFolder.setIcon(new ImageIcon(resourceOpenFolder1));
                jlOpenFolder.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlOpenFolder.setIcon(new ImageIcon(resourceOpenFolder));
                jlOpenFolder.updateUI();
            }
        });

        jlClear.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (recordType.equals("upload")) {
                    NetworkUtils.deleteRecord("upload", fileName);
                    jPanel.removeAll();
                    TransmitPage transmitPage = new TransmitPage(userId,userName);
                    jPanel.add(transmitPage);
                    jPanel.updateUI();
                }if (recordType.equals("download")){
                    NetworkUtils.deleteRecord("download", fileName);
                    jPanel.removeAll();
                    TransmitPage transmitPage = new TransmitPage(userId, userName);
                    jPanel.add(transmitPage);
                    jPanel.updateUI();
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
                jlClear.setIcon(new ImageIcon(resourceClear1));
                jlClear.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlClear.setIcon(new ImageIcon(resourceClear));
                jlClear.updateUI();
            }
        });
    }
}
