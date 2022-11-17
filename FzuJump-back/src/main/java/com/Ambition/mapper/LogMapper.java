package com.Ambition.mapper;

import com.Ambition.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    List<Log> getAllLog();
}
