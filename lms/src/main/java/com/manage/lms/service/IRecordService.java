package com.manage.lms.service;

import com.manage.lms.pojo.RecordOperation;

import java.util.List;

public interface IRecordService {
    public void record(RecordOperation recordOperation);
    public List<RecordOperation> selectList();
}
