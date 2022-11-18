package com.Ambition.service.impl;

import com.Ambition.Utils.AppDateUtils;
import com.Ambition.Utils.Code;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.LogMapper;
import com.Ambition.pojo.Log;
import com.Ambition.pojo.Role;
import com.Ambition.pojo.Score;
import com.Ambition.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    public Map<String, Object> getAllLog(){
        List<Log> list = logMapper.getAllLog();
        PageInfo<Log> pages = new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        List<Log> list1 = pages.getList();
        for (Log log : list1) {
            Date createTime = log.getCreateTime();
            String formatTime = AppDateUtils.getFormatTime( "yyyy-MM-dd HH:mm:ss",createTime);
            log.setTime(formatTime);
        }
        map.put("total",pages.getTotal());
        map.put("pages",pages.getPages());
        map.put("pagenum", pages.getPageNum());
        map.put("list",pages.getList());
        return map;
    }

    public ResultData addLog(String message) {
        ResultData resultData = new ResultData();
        if (message.isEmpty()) {
            resultData.setCode(Code.FALISE);
            resultData.setMsg("信息为空");
        } else {
            logMapper.addLog(message);
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("添加成功");
        }
        System.out.println("=================>执行addLog方法");
        return resultData;
    }

    public ResultData deleteLog(Integer id){
        ResultData resultData = new ResultData();
        logMapper.deleteLog(id);
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("删除成功");
        List<Log> list = logMapper.getAllLog();
        PageInfo<Log> pages = new PageInfo<>(list);
        System.out.println("=================>执行deleteLog方法");
        return resultData;
    }
}
