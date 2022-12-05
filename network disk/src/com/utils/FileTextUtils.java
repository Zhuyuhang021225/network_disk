package com.utils;

import javax.swing.*;
import java.awt.*;

public class FileTextUtils extends JLabel {
    public FileTextUtils(String fileName, int x,int y)
    {
        setText(fileName);
        setFont(new Font("微软雅黑", Font.PLAIN, 12));
        setBounds(x,y,70,30);
    }
}
