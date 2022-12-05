package com.utils;

import com.ui.image.UserInforPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.ui.HomePage.jPanel2;
import static com.ui.HomePage.refreshFileList;
import static com.utils.FileUtils.DOWNLOAD_DIR;

public class FileTypeUtils extends JButton {
    JLabel          jlFileType;

    public FileTypeUtils(String fileType,String fileName,Long userId,String userName,String createtime,Long length,int x,int y) {

        URL resourceType = UserInforPage.class.getResource(fileType);
        jlFileType = new JLabel(new ImageIcon(resourceType));
        jlFileType.setBounds(0,0,60,61);

        setName(fileName);
        setToolTipText("<html><body>"+"名称："+fileName+"<br>"+"修改时间："+createtime+"<br>"+"文件大小："+fileSize(length)+"<body></html>");
        setBounds(x,y,100,100);
        setBackground(Color.white);
        setBorderPainted(false);
        add(jlFileType);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1){

                }else if (e.getButton()==MouseEvent.BUTTON3){

                    JPopupMenu jPopupMenu = new JPopupMenu();
                    jPopupMenu.setBackground(Color.white);

                    JMenuItem jmiOpen = new JMenuItem("打开");
                    jmiOpen.setBackground(Color.white);
                    JMenuItem jmiDownload = new JMenuItem("下载");
                    jmiDownload.setBackground(Color.white);
                    JMenuItem jmiDelete = new JMenuItem("删除");
                    jmiDelete.setBackground(Color.white);
                    JMenuItem jmiInformation = new JMenuItem("详细信息");
                    jmiInformation.setBackground(Color.white);

                    jPopupMenu.add(jmiOpen);
                    jPopupMenu.addSeparator();
                    jPopupMenu.add(jmiDownload);
                    jPopupMenu.addSeparator();
                    jPopupMenu.add(jmiDelete);
                    jPopupMenu.addSeparator();
                    jPopupMenu.add(jmiInformation);

                    jmiOpen.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
                    });
                    jmiDownload.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String fileName=getName();
                            if (fileName==null)
                            {
                                JOptionPane.showMessageDialog(null,"请重新选择文件");
                            }
                            else
                            {
                                NetworkUtils.downLoadFile(fileName,userName,userId,"download");
                                JOptionPane.showMessageDialog(null,"文件已下载至:"+DOWNLOAD_DIR);
                                fileName = null;
                            }
                        }
                    });
                    jmiDelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String fileName=getName();
                            if (fileName==null)
                            {
                                JOptionPane.showMessageDialog(null,"请重新选择文件");
                            }
                            else
                            {
                                NetworkUtils.deleteFile(fileName,userId);
                                JOptionPane.showMessageDialog(null,"你已经成功删除该文件");
                                fileName = null;
                            }
                            jPanel2.removeAll();
                            refreshFileList(NetworkUtils.getFileItems(userId));
                            jPanel2.updateUI();
                        }
                    });

                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
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
    }

    public static String fileSize(Long length)
    {
        String fileSize;
        if (length > 1024000)
        {
            fileSize = String.format("%.1f", (length / 1024000.0));
            return (fileSize+".MB");
        }
        else
        {
            fileSize = String.format("%.1f", (length / 1024.0));
            return (fileSize+".KB");
        }
    }
}
