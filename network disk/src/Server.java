import com.dao.IFileDAO;
import com.dao.IUserDAO;
import com.dao.impl.FIleDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.pojo.FileItem;
import com.pojo.User;
import com.utils.FileUtils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.utils.FileUtils.*;

public class Server {
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private ExecutorService threadPool = Executors.newFixedThreadPool(CPU_COUNT*2);
    private IUserDAO userDAO = new UserDAOImpl();
    private IFileDAO fileDAO = new FIleDAOImpl();


    public synchronized void handleLogin(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        String username = inputStream.readUTF();
        String password = inputStream.readUTF();
        try {
            User user = userDAO.login(username, password);
            if (user == null){
                outputStream.writeLong(-1L);
                return;
            }
            outputStream.writeLong(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleListFiles(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        long userId = inputStream.readLong();
        try {
            List<FileItem> fileItems = fileDAO.selectFileItemsByUserId(userId);
            String json = listToJson(fileItems);
            outputStream.writeUTF(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized void handleUpload(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        String fileName = inputStream.readUTF();
        if("error".equals(fileName)){
            System.out.println("客户端文件错误");
            return;
        }
        long userId = inputStream.readLong();
        try {
            List<User> users = userDAO.selectUserById(userId);
            User user = users.get(0);
            File file = new File(UPLOAD_DIR+"\\"+user.getUsername());
            if (!file.exists()){
                file.mkdirs();
            }
            File uploadFile = new File(UPLOAD_DIR+"\\"+user.getUsername()+ "\\"+fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
            FileUtils.io(inputStream, fileOutputStream);
            fileOutputStream.close();
            FileItem fileItem = fileDAO.selectFileItemByUserIdAndFileName(userId, fileName);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            if (fileItem == null){
                fileItem = new FileItem(System.currentTimeMillis()%1000,fileName,uploadFile.getAbsolutePath(),uploadFile.length(),userId,1,0,time);
                fileDAO.insertFile(fileItem);
            }else {
                fileItem.setLength(uploadFile.length());
                fileItem.setUploadRecord(1);
                fileItem.setCreate_time(time);
                fileDAO.updateFile(fileItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleDownload(DataInputStream inputStream,DataOutputStream outputStream) throws IOException, SQLException {
        String fileName = inputStream.readUTF();
        String userName = inputStream.readUTF();
        long userId = inputStream.readLong();
        String type = inputStream.readUTF();
        File file = new File(UPLOAD_DIR + "\\"+userName+"\\"+ fileName);
        if(file.exists()){
            outputStream.writeUTF(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            FileUtils.io( fileInputStream,outputStream );
            if (type.equals("download")) {
                FileItem fileItem = fileDAO.selectFileItemByUserIdAndFileName(userId, fileName);
                fileItem.setDownloadRecord(1);
                fileDAO.updateFile(fileItem);
            }
        }else{
                outputStream.writeUTF("error");
        }
    }


    public synchronized void handleDelete(DataInputStream inputStream,DataOutputStream outputStream) throws IOException, SQLException {
        String fileName = inputStream.readUTF();
        long userId = inputStream.readLong();
        List<User> users = userDAO.selectUserById(userId);
        User user = users.get(0);
        File file = new File(UPLOAD_DIR + "\\"+user.getUsername()+"\\"+ fileName);
        if(file.exists()){
            outputStream.writeUTF(fileName);
            file.delete();
            fileDAO.deleteFile(fileName,userId);
        }else{
            outputStream.writeUTF("error");
        }
    }

    public synchronized void handleRegister(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        long userId = inputStream.readLong();
        userDAO.regiser(userId,inputStream.readUTF(),inputStream.readUTF(),
                inputStream.readUTF(),inputStream.readUTF());
        try {
            if (userDAO.selectUserById(userId)!=null) {
                outputStream.writeUTF("注册成功");
                return;
            }
            outputStream.writeUTF("失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleSearchFiles(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        List<String> fileNames = JsonToString(inputStream.readUTF());
        Long userId = inputStream.readLong();
        try {
            List<FileItem> fileItems = fileDAO.serachFileItemByFileNameAndUserId(fileNames,userId);
            String json = listToJson(fileItems);
            outputStream.writeUTF(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private synchronized void handleAllUser(DataInputStream inputStream, DataOutputStream outputStream) {
        try {
            List<User> users = userDAO.getAllUser();
            String user = UserToJson(users);
            outputStream.writeUTF(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void handleSelectByUserId(DataInputStream inputStream, DataOutputStream outputStream) {
        try {
            long userId = inputStream.readLong();
            List<User> users = userDAO.selectUserById(userId);
            String user = UserToJson(users);
            outputStream.writeUTF(user);
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleUploadRecord(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        try {
            Long userId = inputStream.readLong();
            Integer uploadRecord = inputStream.readInt();
            List<FileItem> fileItems = fileDAO.selectFileItemsByUserIdAndUploadRecord(userId, uploadRecord);
            String json = listToJson(fileItems);
            outputStream.writeUTF(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (EOFException e) {
        }
    }

    public synchronized void handleDownloadRecord(DataInputStream inputStream,DataOutputStream outputStream) throws IOException {
        try {
            Long userId = inputStream.readLong();
            Integer downloadRecord = inputStream.readInt();
            List<FileItem> fileItems = fileDAO.selectFileItemsByUserIdAndDownloadRecord(userId, downloadRecord);
            String json = listToJson(fileItems);
            outputStream.writeUTF(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (EOFException e) {
        }
    }

    private synchronized void handleDeleteRecord(DataInputStream inputStream, DataOutputStream outputStream) {
        try {
            String type = inputStream.readUTF();
            String fileName = inputStream.readUTF();
            if (type.equals("upload")){
                fileDAO.deleteUploadRecord(fileName);
            }if (type.equals("download")){
                fileDAO.deleteDownloadRecord(fileName);
            }
        } catch (IOException e) {
        }

    }

    private synchronized void handleDeleteAllRecord(DataInputStream inputStream, DataOutputStream outputStream) {
        try {
            Long userId = inputStream.readLong();
            String type = inputStream.readUTF();
            if (type.equals("upload")){
                fileDAO.deleteAllUpload(userId);
            }if (type.equals("download")){
                fileDAO.deleteAllDownload(userId);
            }
        } catch (IOException e) {
        }

    }

    private synchronized void handleLoginByEmail(DataInputStream inputStream, DataOutputStream outputStream) {
        try {
            String email = inputStream.readUTF();
            List<User> user = userDAO.loginByEmail(email);
            String json = UserToJson(user);
            outputStream.writeUTF(json);
        } catch (IOException e) {
        }

    }

    public void start()
    {
        System.out.println("网盘服务器已启动！");
        try(ServerSocket serverSocket = new ServerSocket(FileUtils.PORT);) {

            while(true){
                Socket socket = serverSocket.accept();
                threadPool.execute(()->{
                    try(DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    ){

                        int type = inputStream.readInt();
                        switch(type){
                            case FileUtils.TYPE_LOGIN :
                                handleLogin(inputStream, outputStream);
                                break;

                            case FileUtils.TYPE_LIST :
                                handleListFiles(inputStream, outputStream);
                                break;

                            case FileUtils.TYPE_UPLOAD :
                                handleUpload(inputStream, outputStream);
                                break;

                            case FileUtils.TYPE_DOWNLOAD :
                                handleDownload(inputStream, outputStream);
                                break;

                            case FileUtils.TYPE_DELETE :
                                handleDelete(inputStream, outputStream);
                                break;

                            case FileUtils.TYPE_REGISTER :
                                handleRegister(inputStream, outputStream);

                            case FileUtils.TYPE_SEARCH :
                                handleSearchFiles(inputStream, outputStream);

                            case FileUtils.TYPE_ALLUSER :
                                handleAllUser(inputStream, outputStream);

                            case FileUtils.TYPE_UPLOADRECORD :
                                handleUploadRecord(inputStream, outputStream);

                            case FileUtils.TYPE_DOWNLOADRECORD :
                                handleDownloadRecord(inputStream, outputStream);

                            case FileUtils.TYPE_DELETERECORD :
                                handleDeleteRecord(inputStream, outputStream);

                            case FileUtils.TYPE_DELETEALLRECORD :
                                handleDeleteAllRecord(inputStream,outputStream);

                            case FileUtils.TYPE_LOGINBYEMAIL :
                                handleLoginByEmail(inputStream,outputStream);

                            case FileUtils.TYPE_SELECTBYUSERID :
                                handleSelectByUserId(inputStream,outputStream);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                });

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Server().start();
    }
}
