package com.manage.lms.service.impl;

import com.manage.lms.mapper.IRecordMapper;
import com.manage.lms.pojo.RecordOperation;
import com.manage.lms.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {
    @Autowired
    private IRecordMapper recordMapper;

    @Override
    public void record(RecordOperation recordOperation) {
        recordMapper.record(recordOperation);
    }

    @Override
    public List<RecordOperation> selectList() {
        return recordMapper.selectList();
    }
}
