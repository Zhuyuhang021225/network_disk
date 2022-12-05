package com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pojo.FileItem;
import com.pojo.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 8889;
    public static String UPLOAD_DIR = "D:\\Server\\upload";
    public static String DOWNLOAD_DIR = "D:\\Client\\download";
    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_LIST= 2;
    public static final int TYPE_UPLOAD = 3;
    public static final int TYPE_DOWNLOAD = 4;
    public static final int TYPE_DELETE = 5;
    public static final int TYPE_REGISTER = 6;
    public static final int TYPE_SEARCH = 7;
    public static final int TYPE_ALLUSER = 8;
    public static final int TYPE_UPLOADRECORD = 9;
    public static final int TYPE_DOWNLOADRECORD = 10;
    public static final int TYPE_DELETERECORD = 11;
    public static final int TYPE_DELETEALLRECORD = 12;
    public static final int TYPE_LOGINBYEMAIL = 13;
    public static final int TYPE_SELECTBYUSERID = 14;

    public static List<FileItem> getUploadFiles(){
        File file = new File(UPLOAD_DIR);
        if (!file.exists()) {
            file.mkdirs();
            return null;
        }
        File[] files = file.listFiles();
        List<FileItem> list = new ArrayList<>();
        for(int i = 0; i<files.length; i++)
        {
            File f = files[i];
            if(f.isFile())
            {
                FileItem fIleItem = new FileItem( i+1L, f.getName(),f.getPath(),f.length());
                list.add(fIleItem);
            }
        }
        return list;
    }

    public static String listToJson(List<FileItem> fileItems)
    {
        return  new Gson().toJson(fileItems);
    }

    public static String UserToJson(List<User> users)
    {
        return  new Gson().toJson(users);
    }

    public static String stringToJson(List<String> filenames)
    {
        return  new Gson().toJson(filenames);
    }

    public static List<FileItem> JsonTolist(String json)
    {
        return new Gson().fromJson(json,new TypeToken<List<FileItem>>(){}.getType());
    }

    public static List<User> JsonToUser(String json)
    {
        return new Gson().fromJson(json,new TypeToken<List<User>>(){}.getType());
    }

    public static List<String> JsonToString(String json)
    {
        return new Gson().fromJson(json,new TypeToken<List<String>>(){}.getType());
    }

    public static void io(InputStream in, OutputStream out) throws IOException {
        int len = 0;
        byte[] buff = new byte[1024];
        while((len=in.read(buff))!=-1){
            out.write( buff, 0, len);
        }
    }
}
