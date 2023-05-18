package com.manage.lms.pojo;

import java.util.ArrayList;

public class VoUserInfo {
    private ArrayList<String> role;
    private String name;
    private String introduction;
    private String avatar;

    public VoUserInfo() {
    }

    public VoUserInfo(ArrayList<String> role, String name, String introduction, String avatar) {
        this.role = role;
        this.name = name;
        this.introduction = introduction;
        this.avatar = avatar;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "VoUserInfo{" +
                "role=" + role +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
