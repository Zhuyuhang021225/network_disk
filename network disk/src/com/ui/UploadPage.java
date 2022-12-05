package com.ui;

import com.utils.NetworkUtils;
import javax.swing.*;
import java.io.File;

import static com.ui.HomePage.jPanel2;
import static com.ui.HomePage.refreshFileList;

public class UploadPage {

    public UploadPage(Long userId){

        JFileChooser jFileChooser = new JFileChooser();
        File file = new File("null");
        int opt = jFileChooser.showOpenDialog(null);
        if (opt == JFileChooser.APPROVE_OPTION)
        file = jFileChooser.getSelectedFile();
        if(file.getPath().equals("null"))
        {
            JOptionPane.showMessageDialog(null,"请重新选择文件");
        }
        else
        {
            NetworkUtils.uploadFile(file.getAbsolutePath(),userId);
            JOptionPane.showMessageDialog(null,"上传成功");
        }
        jPanel2.removeAll();
        refreshFileList(NetworkUtils.getFileItems(userId));
        jPanel2.updateUI();
    }
}

