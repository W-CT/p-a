package com.manage.lms.mapper;

import com.manage.lms.pojo.RecordOperation;

import java.util.List;

public interface IRecordMapper {
    public void record(RecordOperation recordOperation);
    public List<RecordOperation> selectList();
}
