package com.Ambition.controller;

import com.Ambition.Utils.Code;
import com.Ambition.Utils.Constant;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.User;
import com.Ambition.service.ScoreService;
import com.Ambition.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "App接口")
@RestController
public class AppController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ScoreService scoreService;

    @Resource
    private UserService userService;

    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(paramType = "query",name = "usercode", value = "用户账号", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query",name = "password", value = "用户密码", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/register")
    public ResultData register(String usercode, String password){
        ResultData resultData = new ResultData();
        User user = userMapper.GetUserBy(null, usercode, null);
        if(user != null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("账号已存在，请重新输入");
        }else if(usercode == null || password == null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("账号或密码为空");
        }else{
            User user1 = new User();
            user1.setUserCode(usercode);
            user1.setUserName("匿名用户");
            user1.setRoleId(7);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user1.setPassword(bCryptPasswordEncoder.encode(password));
            userMapper.addUser1(user1);
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("注册成功");
        }
        return resultData;
    }

    @ApiOperation("排行榜")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
    })
    @PostMapping("/appRank")
    public Map<String, Object> appRank(){
        PageHelper.startPage(1, Constant.LIMIT);
        return scoreService.appGetAllScore(1);
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(paramType = "query",name = "usercode", value = "用户账号", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query",name = "password", value = "用户密码", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/appUpdate")
    public Map<String, Object> appUpdate(String usercode,String password){
        return userService.appUpdate(usercode,password);
    }

    @ApiOperation("添加成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userCode", value = "用户姓名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "rolename", value = "角色", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "jumpFrequency", value = "跳跃高度", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "itemNumber", value = "拾取道具数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/appAdd")
    public ResultData addScore(String userCode,String userName, String rolename, Integer jumpFrequency, Integer itemNumber) {
        return scoreService.addScore(userCode, userName , rolename, jumpFrequency, itemNumber);
    }

    @ApiOperation("查询个人最佳成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "usercode", value = "用户账号", required = true, dataType = "String", dataTypeClass = String.class)
           })
    @PostMapping("/appSearch")
    public Map<String, Object> appUpdate(String usercode){
        return scoreService.appGetScoreByUserCode(usercode);
    }

    @ApiOperation("上传成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "rolename", value = "角色", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userCode", value = "账号", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "jumpFrequency", value = "跳跃高度", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "itemNumber", value = "拾取道具数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/appScoreAdd")
    public ResultData appScoreAdd(String userCode,String userName, String rolename, Integer jumpFrequency, Integer itemNumber){
        return scoreService.addScore(userCode, userName , rolename, jumpFrequency, itemNumber);
    }


}
