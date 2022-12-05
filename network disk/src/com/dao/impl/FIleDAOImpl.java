package com.dao.impl;

import com.dao.IFileDAO;
import com.pojo.FileItem;
import com.utils.JDBCUtils;
import com.utils.JDBCUtilsV2;

import java.sql.SQLException;
import java.util.List;

public class FIleDAOImpl implements IFileDAO {
    @Override
    public List<FileItem> selectFileItemsByUserId(Long userId) throws SQLException {

        return JDBCUtils.query(FileItem.class, "select * from t_file where user_id = ?", userId);
    }

    @Override
    public FileItem selectFileItemByUserIdAndFileName(Long userId, String fileName) throws SQLException {
        List<FileItem> list = JDBCUtils.query(FileItem.class, "select * from t_file where user_id = ? and name = ?",userId,fileName);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public FileItem selectFileItemById(Long id) throws SQLException {
        List<FileItem> list = JDBCUtils.query(FileItem.class, "select * from t_file where id = ?", id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<FileItem> serachFileItemByFileNameAndUserId(List<String> filenames,Long userId) throws SQLException {
        String filename = "";
        for (int i=0;i<filenames.size();i++){
            if (i<filenames.size()-1) {
                filename = filename+filenames.get(i)+"、";
            }else {
                filename = filename+filenames.get(i);
            }
        }
        return JDBCUtils.query(FileItem.class, "select * from t_file where name like '%"+filename+"%' and user_id = ?",userId);
    }

    @Override
    public void insertFile(FileItem fileItem) throws SQLException {
        JDBCUtils.update("insert into t_file(id,name,path,length,user_id,uploadRecord,downloadRecord,create_time) values(?,?,?,?,?,?,?,?)",
                fileItem.getId(),fileItem.getName(), fileItem.getPath(), fileItem.getLength(), fileItem.getUser_id(), fileItem.getUploadRecord(),fileItem.getDownloadRecord(), fileItem.getCreate_time());
    }

    @Override
    public void updateFile(FileItem fileItem) throws SQLException {
        JDBCUtilsV2.update("update t_file set id=?,name=?,path=?,length=?,user_id=?,uploadRecord=?,downloadRecord=?,create_time=? where id=?",
                fileItem.getId(),fileItem.getName(), fileItem.getPath(), fileItem.getLength(), fileItem.getUser_id(), fileItem.getUploadRecord(),fileItem.getDownloadRecord(), fileItem.getCreate_time(), fileItem.getId());
    }

    @Override
    public void deleteFile(String fileName,Long userId) throws SQLException {
        JDBCUtilsV2.update("delete from t_file where user_id=? and name = ?",userId,fileName);
    }

    @Override
    public void deleteUploadRecord(String fileName) {
        JDBCUtilsV2.update("update t_file set uploadRecord = ? where name = ?",0,fileName);
    }

    @Override
    public void deleteDownloadRecord(String fileName) {
        JDBCUtilsV2.update("update t_file set downloadRecord = ? where name = ?",0,fileName);
    }

    @Override
    public void deleteAllUpload(Long userId) {
        JDBCUtilsV2.update("update t_file set uploadRecord = ? where user_id = ?",0,userId);
    }

    @Override
    public void deleteAllDownload(Long userId) {
        JDBCUtilsV2.update("update t_file set downloadRecord = ? where user_id = ?",0,userId);
    }

    @Override
    public List<FileItem> selectFileItemsByUserIdAndUploadRecord(Long userId, Integer uploadRecord) throws SQLException {
        return JDBCUtils.query(FileItem.class, "select * from t_file where user_id = ? and uploadRecord= ?", userId,uploadRecord);
    }

    @Override
    public List<FileItem> selectFileItemsByUserIdAndDownloadRecord(Long userId, Integer downloadRecord) throws SQLException {
        return JDBCUtils.query(FileItem.class, "select * from t_file where user_id = ? and downloadRecord= ?", userId,downloadRecord);
    }


}