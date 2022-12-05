package com.pojo;


public class FileItem {
    private Long id;
    private String name;
    private String path;
    private Long length;
    private Long user_id;
    private Integer uploadRecord;

    public FileItem(Long id, String name, String path, Long length, Long user_id, Integer uploadRecord, Integer downloadRecord, String create_time) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.length = length;
        this.user_id = user_id;
        this.uploadRecord = uploadRecord;
        this.downloadRecord = downloadRecord;
        this.create_time = create_time;
    }

    public Integer getDownloadRecord() {
        return downloadRecord;
    }

    public void setDownloadRecord(Integer downloadRecord) {
        this.downloadRecord = downloadRecord;
    }

    private Integer downloadRecord;
    private String create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getUploadRecord() {
        return uploadRecord;
    }

    public void setUploadRecord(Integer uploadRecord) {
        this.uploadRecord = uploadRecord;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public FileItem(Long id, String name, String path, Long length) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.length = length;
    }

    public FileItem(Long id, String name, String path, Long length, Long user_id, Integer uploadRecord, String create_time) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.length = length;
        this.user_id = user_id;
        this.uploadRecord = uploadRecord;
        this.create_time = create_time;
    }

    public FileItem() {
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", length=" + length +
                '}';
    }

}
