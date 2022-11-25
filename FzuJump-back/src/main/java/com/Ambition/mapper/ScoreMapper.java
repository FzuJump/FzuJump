package com.Ambition.mapper;

import com.Ambition.pojo.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    //获得所有成绩
    List<Score> GetAllScore();
    //根据姓名查询成绩
    List<Score> GetScoreBy(String userName);
    //更新成绩
    void updateScore(Score score);
    //删除成绩
    void deleteScore(int id);
    //增加成绩
    void addScore(Score score);
    //根据  查询成绩
    List<Score> GetAllScoreBy();
    //成绩数量
    int countScore();
    //按照账号获取成绩
    List<Score> GetScoreByUserCode(String userCode);
}
