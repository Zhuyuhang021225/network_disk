package com.utils;

import com.pojo.FileItem;
import com.pojo.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import static com.utils.FileUtils.*;

public class NetworkUtils {

    public static Long login(String username, String password)
    {
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_LOGIN);
            outputStream.writeUTF(username);
            outputStream.writeUTF(password);
            long userId = inputStream.readLong();
            return userId;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getAllUsers () {
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_ALLUSER);
            String json = inputStream.readUTF();
            return JsonToUser( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<FileItem> getFileItems(Long userId){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_LIST);
            outputStream.writeLong(userId);
            String json = inputStream.readUTF();
            return JsonTolist( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void uploadFile( String path, Long userId){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_UPLOAD);
            File file = new File(path);
            if (!file.exists()){
                outputStream.writeUTF("error");
                System.out.println("文件名不存在！");
                return;
            }
            outputStream.writeUTF(file.getName());
            outputStream.writeLong(userId);
            FileInputStream fileInputStream = new FileInputStream(path);
            FileUtils.io(fileInputStream, outputStream);
            outputStream.close();
            fileInputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downLoadFile(String fileName,String userName,Long userId,String type){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_DOWNLOAD);
            outputStream.writeUTF(fileName);
            outputStream.writeUTF(userName);
            outputStream.writeLong(userId);
            outputStream.writeUTF(type);
            String name = inputStream.readUTF();
            if("error".equals(name))
            {
                System.out.println("服务器不存在该文件");
                return;
            }
            File file = new File(DOWNLOAD_DIR);
            if(!file.exists()){
                file.mkdirs();
            }
            if (type.equals("download")) {
                FileOutputStream fileOutputStream = new FileOutputStream(DOWNLOAD_DIR + "\\" + fileName);
                FileUtils.io(inputStream, fileOutputStream);
                fileOutputStream.close();
            }else{
                FileOutputStream fileOutputStream = new FileOutputStream("D:\\Client\\temp\\" + fileName);
                FileUtils.io(inputStream, fileOutputStream);
                fileOutputStream.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName, Long userId){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_DELETE);
            outputStream.writeUTF(fileName);
            outputStream.writeLong(userId);
            String name = inputStream.readUTF();
            if("error".equals(name)){
                System.out.println("服务器不存在该文件");
                return;
            }
            System.out.println("删除完毕");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String register(Long userId, String phone,String userName,String password, String email){
        String result=null;
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_REGISTER);
            outputStream.writeLong(userId);
            outputStream.writeUTF(phone);
            outputStream.writeUTF(userName);
            outputStream.writeUTF(password);
            outputStream.writeUTF(email);
            result = inputStream.readUTF();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<FileItem> searchFile(List<String> fileName, Long userId){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_SEARCH);
            outputStream.writeUTF(stringToJson(fileName));
            outputStream.writeLong(userId);
            String json = inputStream.readUTF();
            return JsonTolist( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<FileItem> uploadRecord(Long userId,int uploadRecord){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_UPLOADRECORD);
            outputStream.writeLong(userId);
            outputStream.writeInt(uploadRecord);
            String json = inputStream.readUTF();
            return JsonTolist( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<FileItem> downloadRecord(Long userId,int downloadRecord){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_DOWNLOADRECORD);
            outputStream.writeLong(userId);
            outputStream.writeInt(downloadRecord);
            String json = inputStream.readUTF();
            return JsonTolist( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteRecord(String type,String fileName){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_DELETERECORD);
            outputStream.writeUTF(type);
            outputStream.writeUTF(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllRecord(Long userId,String type){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_DELETEALLRECORD);
            outputStream.writeLong(userId);
            outputStream.writeUTF(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> LoginByEmail(String email){
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            outputStream.writeInt(TYPE_LOGINBYEMAIL);
            outputStream.writeUTF(email);
            String json = inputStream.readUTF();
            return JsonToUser( json );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> selectUserByUserId (Long userId) {
        try(Socket socket = new Socket( IP, PORT)
        ){
            DataInputStream inputStream = new DataInputStream( socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TYPE_SELECTBYUSERID);
            outputStream.writeLong(userId);
            String json = inputStream.readUTF();
            return JsonToUser( json );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
