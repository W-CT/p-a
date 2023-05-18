package com.manage.lms.mapper;


import com.manage.lms.pojo.Directory;

import java.util.List;

public interface IDirectoryMapper {
    public List<Directory> selectAll();

    //分类添加
    public void insert(Directory directory);

    public Directory selectById(Long id);

    public void update(Directory directory);

    public void delete(Long id);


}
