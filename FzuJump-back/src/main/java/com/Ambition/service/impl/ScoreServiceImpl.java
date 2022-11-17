package com.Ambition.service.impl;

import com.Ambition.Utils.Code;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.RoleMapper;
import com.Ambition.mapper.ScoreMapper;
import com.Ambition.pojo.Role;
import com.Ambition.pojo.Score;
import com.Ambition.service.ScoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private RoleMapper roleMapper;

    public ResultData GetAllGrades(){
        ResultData<Object> resultData = new ResultData<>();
        List<Score> allScore = scoreMapper.GetAllScore();
        PageInfo<Score> pages = new PageInfo<>(allScore);
        resultData.setCode(200);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pages.getTotal());
        map.put("pages", pages.getPages());
        map.put("pagenum", pages.getPageNum());
        map.put("list", pages.getList());
        resultData.setData(map);
        System.out.println("=================>执行GetAllGrades方法");
        System.out.println(resultData);
        return resultData;
    }

    public ResultData searchScore(String goodsName){
        ResultData<Object> resultData = new ResultData<>();
        List<Score> stocks = scoreMapper.GetScoreBy(goodsName);
        if (stocks != null && !stocks.isEmpty()){
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("查询成功");
            resultData.setData(stocks);
        }else {
            resultData.setCode(Code.FALISE);
            resultData.setMsg("没有该用户的成绩数据");
        }
        System.out.println("=================>执行searchStock方法");
        return resultData;
    }

    public ResultData updateScore(int id, String userName, String rolename, int jumpFrequency, int itemNumber){
        Score score = new Score();
        Role role = roleMapper.GetRoleBy(rolename, null);
        score.setId(id);
        score.setItemNumber(itemNumber);
        score.setJumpFrequency(jumpFrequency);
        score.setUserRole(role.getId());
        score.setUserName(userName);
        scoreMapper.updateScore(score);
        ResultData resultData = new ResultData();
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("成绩修改成功");
        System.out.println("=================>执行updateScore方法");
        return resultData;
    }

    public ResultData deleteScore(int id) {
        ResultData resultData = new ResultData();
        scoreMapper.deleteScore(id);
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("商品删除成功");
        List<Score> allScore = scoreMapper.GetAllScore();
        PageInfo<Score> pages = new PageInfo<>(allScore);
        resultData.setCode(200);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pages.getTotal());
        map.put("pages", pages.getPages());
        map.put("pagenum", pages.getPageNum());
        map.put("list", pages.getList());
        resultData.setData(map);
        System.out.println("=================>执行deleteScore方法");
        return resultData;
    }

    public ResultData addScore(String userName, String rolename, int jumpFrequency, int itemNumber){
        Score score = new Score();
        Role role = roleMapper.GetRoleBy(rolename, null);
        score.setItemNumber(itemNumber);
        score.setJumpFrequency(jumpFrequency);
        score.setUserRole(role.getId());
        score.setUserName(userName);
        scoreMapper.addScore(score);
        ResultData resultData = new ResultData();
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("添加成功");
        System.out.println("=================>执行addScore方法");
        return resultData;
    }
}
