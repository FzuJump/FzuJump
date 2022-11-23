package com.Ambition.service.impl;

import com.Ambition.Utils.AppDateUtils;
import com.Ambition.Utils.Code;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.LogMapper;
import com.Ambition.mapper.RoleMapper;
import com.Ambition.mapper.ScoreMapper;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.Log;
import com.Ambition.pojo.Role;
import com.Ambition.pojo.User;
import com.Ambition.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LogMapper logMapper;

    @Resource
    private ScoreMapper scoreMapper;


    public ResultData getIndex(String userCode){
        ResultData resultData = new ResultData();
        User user = userMapper.GetUserBy(null, userCode, null);
        Role role = roleMapper.GetRoleBy(null, user.getRoleId());
        int countRole = roleMapper.countRole();
        int countUser = userMapper.countUser();
        int countLog = logMapper.countLog();
        int countScore = scoreMapper.countScore();
        List<Log> allLog = logMapper.getAllLog();
        for (Log log : allLog) {
            Date createTime = log.getCreateTime();
            String formatTime = AppDateUtils.getFormatTime( "yyyy-MM-dd HH:mm:ss",createTime);
            log.setTime(formatTime);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUserName());
        map.put("role",role.getRolename());
        map.put("countRole", String.valueOf(countRole));
        map.put("countUser", String.valueOf(countUser));
        map.put("countLog", String.valueOf(countLog));
        map.put("countScore", String.valueOf(countScore));
        map.put("Log", allLog);
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("查询成功");
        resultData.setData(map);
        return resultData;
    }
}
