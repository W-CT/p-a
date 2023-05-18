package com.manage.lms.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manage.lms.pojo.RecordOperation;
import com.manage.lms.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private IRecordService recordService;

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    @ResponseBody
    public List<RecordOperation> input() {
        System.out.println(recordService.selectList());
        return recordService.selectList();
    }
}
