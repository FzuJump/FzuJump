package com.Ambition.mapper;

import com.Ambition.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    //获得所有日志
    List<Log> getAllLog();
    //增加日志
    void addLog(String message);
    //删除日志
    void deleteLog(Integer id);
    //日志数量
    int countLog();
}
