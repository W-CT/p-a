package com.manage.lms.service.impl;

import com.manage.lms.mapper.IUserMapper;
import com.manage.lms.pojo.User;
import com.manage.lms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;
    @Override//登录业务
    public User login(String userName, String password) {
        return userMapper.login(userName,password);
    }

    @Override//注册
    public void register(String userName, String password) {
        userMapper.register(userName, password);
    }

    @Override
    public String selectUserName(String userName) {
        return userMapper.selectUserName(userName);
    }
}
