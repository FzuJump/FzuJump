package com.Ambition.service;

import com.Ambition.dto.ResultData;

public interface ScoreService {
    ResultData GetAllGrades(Integer state);

    ResultData searchScore(String userName);

    ResultData updateScore(Integer id, String userName, Integer jumpFrequency, Integer itemNumber);

    ResultData deleteScore(int id);

    ResultData addScore(String userName, String rolename, Integer jumpFrequency, Integer itemNumber);
}
