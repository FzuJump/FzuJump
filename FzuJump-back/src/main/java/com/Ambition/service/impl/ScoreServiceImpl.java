package com.Ambition.service.impl;

import com.Ambition.Utils.Code;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.RoleMapper;
import com.Ambition.mapper.ScoreMapper;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.Role;
import com.Ambition.pojo.Score;
import com.Ambition.pojo.User;
import com.Ambition.service.ScoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    public ResultData GetAllGrades(Integer state){
        ResultData<Object> resultData = new ResultData<>();
        List<Score> allScore;
        if(state == 0){
            allScore = scoreMapper.GetAllScore();
        }
       else{
            allScore = scoreMapper.GetAllScoreBy();

        }
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

    public ResultData updateScore(Integer id, String userName,Integer jumpFrequency, Integer itemNumber){
        ResultData resultData = new ResultData();
        if(userName.isEmpty()||jumpFrequency == null||itemNumber == null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("存在信息为空");
        }
        else{
            Score score = new Score();
            score.setId(id);
            score.setItemNumber(itemNumber);
            score.setJumpFrequency(jumpFrequency);
            score.setUserName(userName);
            scoreMapper.updateScore(score);
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("成绩修改成功");
        }
        System.out.println("=================>执行updateScore方法");
        return resultData;
    }

    public ResultData deleteScore(int id) {
        ResultData resultData = new ResultData();
        scoreMapper.deleteScore(id);
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("商品删除成功");
        System.out.println("=================>执行deleteScore方法");
        return resultData;
    }

    public ResultData addScore(String userCode,String userName, String rolename, Integer jumpFrequency, Integer itemNumber){
        ResultData resultData = new ResultData();
        System.out.println(userCode);
        User user = userMapper.GetUserBy(null, userCode, null);
        if (user == null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("不存在此用户");
        }
        else if(userName.isEmpty()||rolename.isEmpty()||jumpFrequency == null||itemNumber == null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("存在信息为空");
        }
        else{
            Score score = new Score();
            score.setItemNumber(itemNumber);
            score.setJumpFrequency(jumpFrequency);
            score.setUserRole(user.getRoleId());
            score.setUserName(user.getUserName());
            score.setUserCode(userCode);
            scoreMapper.addScore(score);
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("添加成功");
        }
        System.out.println("=================>执行addScore方法");
        return resultData;
    }

    public Map<String,Object> appGetAllScore(Integer state){

        List<Score> allScore = scoreMapper.GetAllScoreBy();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Code",200);
        for(int i = 0 ;i<10 ;i++){
            map.put("n"+String.valueOf(i*3), allScore.get(i).getUserCode());
            map.put("n"+String.valueOf(i*3+1), allScore.get(i).getJumpFrequency());
            map.put("n"+String.valueOf(i*3+2), allScore.get(i).getItemNumber());
        }
        System.out.println("=================>执行appGetAllScore方法");
        return map;
    }

    public Map<String,Object> appGetScoreByUserCode(String userCode){
        HashMap<String, Object> map = new HashMap<>();
        List<Score> scores = scoreMapper.GetScoreByUserCode(userCode);
        if(scores.isEmpty()){
            map.put("Code",300);
            map.put("msg","您尚未上传过成绩");
        }
        else{
            int JumpFrequency = 0;
            int ItemNumber = 0;
            for (Score score : scores) {
                if(score.getJumpFrequency()>JumpFrequency){
                    JumpFrequency = score.getJumpFrequency();
                }
                if(score.getItemNumber()>ItemNumber){
                    ItemNumber = score.getItemNumber();
                }
            }
            map.put("Code",200);
            map.put("msg","查询成功");
            map.put("JumpFrequency",JumpFrequency);
            map.put("ItemNumber",ItemNumber);
        }
        return map;
    }
}
