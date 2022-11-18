package com.Ambition.service.impl;

import com.Ambition.Utils.AppDateUtils;
import com.Ambition.mapper.LogMapper;
import com.Ambition.pojo.Log;
import com.Ambition.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
}
