package com.dao;

import com.pojo.FileItem;

import java.sql.SQLException;
import java.util.List;

public interface IFileDAO {

    List<FileItem> selectFileItemsByUserId(Long userId) throws SQLException;

    FileItem selectFileItemByUserIdAndFileName(Long userId, String fileName) throws SQLException;

    FileItem selectFileItemById(Long id) throws SQLException;

    List<FileItem> serachFileItemByFileNameAndUserId(List<String> names, Long userId) throws SQLException;

    void insertFile(FileItem fileItem) throws SQLException;

    void updateFile(FileItem fileItem) throws SQLException;

    void deleteFile(String fileName,Long userId) throws SQLException;

    void deleteUploadRecord(String fileName);

    void deleteDownloadRecord(String fileName);

    void deleteAllUpload(Long userId);

    void deleteAllDownload(Long userId);

    List<FileItem> selectFileItemsByUserIdAndUploadRecord(Long userId,Integer uploadRecord) throws SQLException;

    List<FileItem> selectFileItemsByUserIdAndDownloadRecord(Long userId,Integer downloadRecord) throws SQLException;
}
