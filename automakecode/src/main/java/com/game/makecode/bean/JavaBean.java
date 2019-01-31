package com.game.makecode.bean;

import java.util.ArrayList;
import java.util.List;

public class JavaBean {
    private String fileName;
    private List<String> property = new ArrayList<>();
    private List<String> comment = new ArrayList<>();
    private List<String> clientOrServer = new ArrayList<>();
    private List<String> dataType = new ArrayList<>();
    private List<String> blank = new ArrayList<>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public List<String> getClientOrServer() {
        return clientOrServer;
    }

    public void setClientOrServer(List<String> clientOrServer) {
        this.clientOrServer = clientOrServer;
    }

    public List<String> getDataType() {
        return dataType;
    }

    public List<String> getBlank() {
        return blank;
    }

    public void setBlank(List<String> blank) {
        this.blank = blank;
    }

    public void setDataType(List<String> dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "fileName='" + fileName + '\'' +
                ", property=" + property +
                ", comment=" + comment +
                ", clientOrServer=" + clientOrServer +
                ", dataType=" + dataType +
                '}';
    }
}

