package com.manage.lms.service.impl;

import com.manage.lms.mapper.IDirectoryMapper;
import com.manage.lms.pojo.Directory;
import com.manage.lms.service.IDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryServiceImpl implements IDirectoryService {
    @Autowired
    private IDirectoryMapper directoryMapper;
    @Override
    public List<Directory> selectAll() {
        return directoryMapper.selectAll();
    }

    @Override
    public void insert(Directory directory) {
        directoryMapper.insert(directory);
    }

    @Override
    public Directory selectById(Long id) {
        return directoryMapper.selectById(id);
    }

    @Override
    public void saveOrUpdate(Directory directory) {
        if(directory.getId()==null){
            directoryMapper.insert(directory);
        }else {
            directoryMapper.update(directory);
        }
    }

    @Override
    public void delete(Long id) {
        directoryMapper.delete(id);
    }

    @Override
    public void update(Directory directory) {
        directoryMapper.update(directory);
    }


}
