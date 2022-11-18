package com.Ambition.mapper;

import com.Ambition.pojo.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    List<Score> GetAllScore();

    List<Score> GetScoreBy(String userName);

    void updateScore(Score score);

    void deleteScore(int id);

    void addScore(Score score);
}
