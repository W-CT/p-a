package com.manage.lms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RecordOperation {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date timestamp;
    private String color;
    private String icon;
    private String content;

    public RecordOperation(){

    }

    public RecordOperation(Date timestamp, String color, String icon, String content) {
        this.timestamp = timestamp;
        this.color = color;
        this.icon = icon;
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RecordOperation{" +
                "timestamp=" + timestamp +
                ", color='" + color + '\'' +
                ", icon='" + icon + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
