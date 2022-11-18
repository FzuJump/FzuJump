package com.Ambition.service;

import com.Ambition.dto.ResultData;

import java.util.Map;

public interface LogService {
    Map<String, Object> getAllLog();

    ResultData addLog(String message);

    ResultData deleteLog(Integer id);
}
