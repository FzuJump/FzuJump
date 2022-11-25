package com.Ambition.service;

import com.Ambition.dto.ResultData;

import java.util.Map;

public interface ScoreService {
    ResultData GetAllGrades(Integer state);

    ResultData searchScore(String userName);

    ResultData updateScore(Integer id, String userName, Integer jumpFrequency, Integer itemNumber);

    ResultData deleteScore(int id);

    ResultData addScore(String userCode,String userName, String rolename, Integer jumpFrequency, Integer itemNumber);

    Map<String,Object> appGetAllScore(Integer state);

    Map<String,Object> appGetScoreByUserCode(String userCode);
}
