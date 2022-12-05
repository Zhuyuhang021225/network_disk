package com.ui;

import com.pojo.FileItem;
import com.ui.image.UserInforPage;
import com.utils.NetworkUtils;
import com.utils.RecordUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

import static com.ui.HomePage.fileType;
import static com.ui.HomePage.userName;


public class TransmitPage extends JPanel {
JPanel             jPanel,                  jPanel1,            jPanel2;
JPanel             jpMenuBar;
JButton            jbUPloadRecord,          jbDownloadRecord;
JLabel             jlUPloadRecord,          jlDownloadRecord;
JLabel             jlUPloadRecordText,      jlDownloadRecordText;
JPanel             jpChoose,                jpChoose1;
JLabel             jlCount,                 jlCount1;
JButton            jbClear,                 jbClear1;
public static  Long   userId;

    public TransmitPage(Long userid,String userName){

        userId = userid;
        setBackground(Color.WHITE);
        setBounds(0,0,1055,665);
        setLayout(null);

        jpMenuBar = new JPanel();
        jpMenuBar.setBackground(Color.white);
        jpMenuBar.setLayout(null);
        jpMenuBar.setBounds(0,0,160,665);
        add(jpMenuBar);

        jbUPloadRecord = new JButton();
        jbUPloadRecord.setBounds(0,5,160,35);
        jbUPloadRecord.setBorderPainted(false);
        jbUPloadRecord.setBackground(Color.white);
        jbUPloadRecord.setLayout(null);
        jpMenuBar.add(jbUPloadRecord);

        URL resourceUpload = UserInforPage.class.getResource("upload.png");
        URL resourceUpload2 = UserInforPage.class.getResource("upload2.png");
        jlUPloadRecord = new JLabel(new ImageIcon(resourceUpload2));
        jlUPloadRecord.setBounds(34,10,14,14);
        jbUPloadRecord.add(jlUPloadRecord);

        jlUPloadRecordText = new JLabel("上传记录");
        jlUPloadRecordText.setFont(new Font("微软雅黑", Font.BOLD, 12));
        jlUPloadRecordText.setForeground(Color.blue);
        jlUPloadRecordText.setBounds(60,3,60,30);
        jbUPloadRecord.add(jlUPloadRecordText);

        jpChoose = new JPanel();
        jpChoose.setBounds(0,0,5,35);
        jpChoose.setBackground(Color.blue);
        jbUPloadRecord.add(jpChoose);

        jpChoose1 = new JPanel();
        jpChoose1.setBounds(0,0,5,35);
        jpChoose1.setBackground(Color.white);

        jbDownloadRecord = new JButton();
        jbDownloadRecord.setBounds(0,40,160,35);
        jbDownloadRecord.setBorderPainted(false);
        jbDownloadRecord.setBackground(Color.white);
        jbDownloadRecord.setLayout(null);
        jpMenuBar.add(jbDownloadRecord);

        URL resourceDownload = UserInforPage.class.getResource("download.png");
        URL resourceDownload2 = UserInforPage.class.getResource("download2.png");
        jlDownloadRecord = new JLabel(new ImageIcon(resourceDownload));
        jlDownloadRecord.setBounds(34,10,14,14);
        jbDownloadRecord.add(jlDownloadRecord);

        jlDownloadRecordText = new JLabel("下载记录");
        jlDownloadRecordText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        jlDownloadRecordText.setForeground(Color.BLACK);
        jlDownloadRecordText.setBounds(60,3,60,30);
        jbDownloadRecord.add(jlDownloadRecordText);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(161,0,895,665);
        add(jPanel);

        jPanel2 = new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBounds(0,0,1000,40);
        jPanel.add(jPanel2);

        jPanel1 = new JPanel();
        jPanel1.setLayout(null);
        jPanel1.setBounds(0,0,1000,900);

        JScrollPane jScrollPane = new JScrollPane(jPanel1);
        jScrollPane.setBounds(0,40,1000,900);
        jPanel.add(jScrollPane);

        jbUPloadRecord.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jbUPloadRecord.add(jpChoose);
                jlUPloadRecord.setIcon(new ImageIcon(resourceUpload2));
                jlUPloadRecordText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlUPloadRecordText.setForeground(Color.blue);
                jbUPloadRecord.updateUI();

                jbDownloadRecord.add(jpChoose1);
                jlDownloadRecord.setIcon(new ImageIcon(resourceDownload));
                jlDownloadRecordText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlDownloadRecordText.setForeground(Color.BLACK);
                jbDownloadRecord.updateUI();

                jPanel2.removeAll();
                jPanel1.removeAll();
                uploadRecord();
                jPanel2.updateUI();
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

        jbDownloadRecord.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jbUPloadRecord.add(jpChoose1);
                jlUPloadRecord.setIcon(new ImageIcon(resourceUpload));
                jlUPloadRecordText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                jlUPloadRecordText.setForeground(Color.BLACK);
                jbUPloadRecord.updateUI();

                jbDownloadRecord.add(jpChoose);
                jlDownloadRecord.setIcon(new ImageIcon(resourceDownload2));
                jlDownloadRecordText.setFont(new Font("微软雅黑", Font.BOLD, 12));
                jlDownloadRecordText.setForeground(Color.blue);
                jbDownloadRecord.updateUI();

                jPanel2.removeAll();
                jPanel1.removeAll();
                downloadRecord();
                jPanel2.updateUI();
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
        uploadRecord();
    }

    public void uploadRecord(){
        List<FileItem> fileItems = NetworkUtils.uploadRecord(userId,1);

        jlCount =new JLabel("共上传完成"+fileItems.size()+"个文件!");
        jlCount.setForeground(Color.gray);
        jlCount.setFont(new Font("微软雅黑",Font.BOLD,20));
        jlCount.setBounds(10,0,200,40);
        jPanel2.add(jlCount);

        jbClear = new JButton("清除所有记录");
        jbClear.setBorderPainted(false);
        jbClear.setContentAreaFilled(false);
        jbClear.setBounds(730,10,110,20);
        jPanel2.add(jbClear);

        jbClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NetworkUtils.deleteAllRecord(userId,"upload");
                ClientForm.jPanel.removeAll();
                TransmitPage transmitPage = new TransmitPage(userId,userName);
                ClientForm.jPanel.add(transmitPage);
                ClientForm.jPanel.updateUI();
            }
        });

        if (fileItems.size()!= 0){
            RecordUtils[] recordUtils = new RecordUtils[fileItems.size()];
            int x=0,y=0;
            for (int i =0;i<recordUtils.length;i++){
                recordUtils[i] = new RecordUtils("upload",fileType(fileItems.get(i).getName()),fileItems.get(i).getName(),
                        userId,userName,fileItems.get(i).getCreate_time(),fileItems.get(i).getLength(),x,y);
                jPanel1.add(recordUtils[i]);
                y=y+61;
            }
        }else{
            jPanel1.setBackground(Color.WHITE);

            URL resourceType = UserInforPage.class.getResource("empty.png");
            JLabel jlEmpty = new JLabel(new ImageIcon(resourceType));
            jlEmpty.setBounds(320,150,181,127);
            jPanel1.add(jlEmpty);

            JLabel jlEmpty1 = new JLabel("当前没有上传记录喔~");
            jlEmpty1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
            jlEmpty1.setBounds(350,270,220,40);
            jPanel1.add(jlEmpty1);
        }
    }

    public void downloadRecord(){
        List<FileItem> fileItems = NetworkUtils.downloadRecord(userId,1);

        jlCount1 =new JLabel("共下载完成"+fileItems.size()+"个文件!");
        jlCount1.setForeground(Color.gray);
        jlCount1.setFont(new Font("微软雅黑",Font.BOLD,20));
        jlCount1.setBounds(10,0,200,40);
        jPanel2.add(jlCount1);

        jbClear1 = new JButton("清除所有记录");
        jbClear1.setBorderPainted(false);
        jbClear1.setContentAreaFilled(false);
        jbClear1.setBounds(730,10,110,20);
        jPanel2.add(jbClear1);

        jbClear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NetworkUtils.deleteAllRecord(userId,"download");
                ClientForm.jPanel.removeAll();
                TransmitPage transmitPage = new TransmitPage(userId,userName);
                ClientForm.jPanel.add(transmitPage);
                ClientForm.jPanel.updateUI();
            }
        });

        if (fileItems.size()!= 0){
            RecordUtils[] recordUtils = new RecordUtils[fileItems.size()];
            int x=0,y=0;
            for (int i =0;i<recordUtils.length;i++){
                recordUtils[i] = new RecordUtils("download",fileType(fileItems.get(i).getName()),fileItems.get(i).getName(),
                        userId,userName,fileItems.get(i).getCreate_time(),fileItems.get(i).getLength(),x,y);
                jPanel1.add(recordUtils[i]);
                y=y+61;
            }
        }else{
            jPanel1.setBackground(Color.WHITE);

            URL resourceType = UserInforPage.class.getResource("empty.png");
            JLabel jlEmpty = new JLabel(new ImageIcon(resourceType));
            jlEmpty.setBounds(320,150,181,127);
            jPanel1.add(jlEmpty);

            JLabel jlEmpty1 = new JLabel("当前没有下载记录喔~");
            jlEmpty1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
            jlEmpty1.setBounds(350,270,220,40);
            jPanel1.add(jlEmpty1);
        }
    }
}