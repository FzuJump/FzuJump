package com.Ambition.service;

import com.Ambition.dto.ResultData;

public interface ScoreService {
    ResultData GetAllGrades();

    ResultData searchScore(String userName);

    ResultData updateScore(int id, String userName, String rolename, int jumpFrequency, int itemNumber);

    ResultData deleteScore(int id);

    ResultData addScore(String userName, String rolename, int jumpFrequency, int itemNumber);
}
