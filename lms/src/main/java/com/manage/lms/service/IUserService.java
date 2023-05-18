package com.manage.lms.service;

import com.manage.lms.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {
    User login(String userName,String password);//登录
    void register(String userName,String password);//注册接口
    String selectUserName (String userName);//用户名注册去重
}
