package com.Ambition.service.impl;

import com.Ambition.Utils.AppDateUtils;
import com.Ambition.Utils.Code;
import com.Ambition.Utils.RedisCache;
import com.Ambition.dto.ResultData;
import com.Ambition.dto.UserLogin;
import com.Ambition.mapper.LogMapper;
import com.Ambition.mapper.RoleMapper;
import com.Ambition.mapper.ScoreMapper;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.Log;
import com.Ambition.pojo.Role;
import com.Ambition.pojo.User;
import com.Ambition.service.IndexService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    public RedisTemplate redisTemplate;

    @Resource
    private RedisCache redisCache;

    public ResultData getIndex(String userCode){
        ResultData resultData = new ResultData();
        User user = userMapper.GetUserBy(null, userCode, null);
        Role role = roleMapper.GetRoleBy(null, user.getRoleId());
        int countRole = roleMapper.countRole();
        int countUser = userMapper.countUser();
        int countLog = logMapper.countLog();
        int countScore = scoreMapper.countScore();
        Set keys = redisTemplate.keys("user*");
        ArrayList<Object> objects = new ArrayList<>();
        for (Object key : keys) {
            UserLogin cacheObject = redisCache.getCacheObject(String.valueOf(key));
            User user1 = userMapper.GetUserBy(cacheObject.getUser().getId(), null, null);
            Role role1 = roleMapper.GetRoleBy(null, user1.getRoleId());
            user1.setRole(role1);
            objects.add(user1);
        }
        assert keys != null;
        int size = keys.size();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUserName());
        map.put("role",role.getRolename());
        map.put("countRole", String.valueOf(size));
        map.put("countUser", String.valueOf(countUser));
        map.put("countLog", String.valueOf(countLog));
        map.put("countScore", String.valueOf(countScore));
        map.put("onlineUser",objects);
        resultData.setCode(Code.SUCCESS);
        resultData.setMsg("查询成功");
        resultData.setData(map);
        return resultData;
    }
}
