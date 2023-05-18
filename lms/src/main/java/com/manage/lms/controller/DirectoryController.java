package com.manage.lms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.lms.pojo.Directory;
import com.manage.lms.service.IDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/directory")
public class DirectoryController {
    @Autowired
    private IDirectoryService directoryService;

    //分页显示分类列表
    @RequestMapping(value = "/pageinfo", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Directory> getPageInfo(int pageNum, int pageSize) {
        //当前第几页和每页记录条数
        PageHelper.startPage(pageNum, pageSize);
        //获取总数据
        List<Directory> list = directoryService.selectAll();
        //获得当前分页对象
        PageInfo<Directory> pageInfo = new PageInfo<Directory>(list);
        System.out.println(pageInfo);
        return pageInfo;
    }

    //根据类别ID查询数据段(*编辑数据*)
    @RequestMapping(value = "/getDirectoryById", method = RequestMethod.GET)
    @ResponseBody
    public Directory selectById(Long id) {
        return directoryService.selectById(id);
    }

    //编辑分类
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Directory directory) {
        directoryService.update(directory);
        return "success";
    }

    //删除数据
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(Long id) {
        directoryService.delete(id);
        return "success";
    }

    //添加数据
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String adddirectory(@RequestBody Directory directory) {
        directoryService.insert(directory);
        return "success";
    }
}
