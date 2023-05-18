package com.manage.lms.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.lms.mapper.IBookMapper;
import com.manage.lms.mapper.IDirectoryMapper;
import com.manage.lms.mapper.IRecordMapper;
import com.manage.lms.pojo.Book;
import com.manage.lms.pojo.Directory;
import com.manage.lms.pojo.RecordOperation;
import com.manage.lms.service.IBookService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Aspect
public class record {
    @Autowired
    private IRecordMapper recordMapper;
    @Autowired
    private IBookMapper bookMapper;
    @Autowired
    private IDirectoryMapper directoryMapper;

    @Pointcut(value = "execution(public void com.manage.lms.service.impl.*.insert(..))")// 创建添加功能的切点
    public void insert() {
    }

    @Pointcut(value = "execution(public void com.manage.lms.service.impl.*.update(..))")// 创建更新功能的切点
    public void update() {
    }

    @AfterReturning("insert()")// 在insert方法return后执行以下方法体
    public void record(JoinPoint jp) {
        System.out.println("记录成功");
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        Object object = args[0];
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        String name = (String) jsonObject.get("name");
        System.out.println(name);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(sf.format(new Date()));
            RecordOperation ro = new RecordOperation();
            ro.setColor("#54FF9F");
            ro.setContent("添加了:" + name);
            ro.setTimestamp(date);
            recordMapper.record(ro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Before(value = "execution(public void com.manage.lms.service.impl.BookServiceImpl.delete(..))")
    public void delete1(JoinPoint jp) {
        System.out.println("记录成功");
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        Object object = args[0];
        Long id = (Long) object;
        Book book = bookMapper.selectById(id);
        String name = book.getName();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(sf.format(new Date()));
            RecordOperation ro = new RecordOperation();
            ro.setColor("#FF3030");
            ro.setContent("删除了:" + name);
            ro.setTimestamp(date);
            recordMapper.record(ro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Before(value = "execution(public void com.manage.lms.service.impl.DirectoryServiceImpl.delete(..))")
    public void delete2(JoinPoint jp) {
        System.out.println("记录成功");
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        Object object = args[0];
        Long id = (Long) object;
        Directory directory = directoryMapper.selectById(id);
        String name = directory.getName();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(sf.format(new Date()));
            RecordOperation ro = new RecordOperation();
            ro.setColor("#FF3030");
            ro.setContent("删除了:" + name);
            ro.setTimestamp(date);
            recordMapper.record(ro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @AfterReturning("update()")
    public void update(JoinPoint jp) {
        System.out.println("记录成功");
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        Object object = args[0];
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        String name = (String) jsonObject.get("name");
        System.out.println(name);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(sf.format(new Date()));
            RecordOperation ro = new RecordOperation();
            ro.setColor("#00BFFF");
            ro.setContent("更新了:" + name);
            ro.setTimestamp(date);
            recordMapper.record(ro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
