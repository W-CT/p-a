package com.manage.lms.mapper;

import com.manage.lms.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface IUserMapper {
    User login(@Param("userName") String userName, @Param("password") String password);//登录接口
    void register(@Param("userName") String userName, @Param("password") String password);//注册接口
    String selectUserName (@Param("userName") String userName);//用户名注册去重
}
