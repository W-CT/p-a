package com.manage.lms.service;

import com.manage.lms.pojo.Directory;

import java.util.List;

public interface IDirectoryService {
    public List<Directory> selectAll();

    public void insert(Directory directory);

    Directory selectById(Long id);

    public void saveOrUpdate(Directory directory);

    public void delete(Long id);

    public void update(Directory directory);
}
