package com.Ambition.controller;

import com.Ambition.Utils.Constant;
import com.Ambition.dto.ResultData;
import com.Ambition.service.ScoreService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "成绩管理")
@RestController
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @ApiOperation("修改成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "jumpFrequency", value = "跳跃高度", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "itemNumber", value = "拾取道具数", required = true, dataType = "Integer", dataTypeClass = Integer.class),
    })
    @GetMapping("/score/update")
    public ResultData updateGoods(Integer id, String userName, Integer jumpFrequency, Integer itemNumber) {
        return scoreService.updateScore(id, userName,  jumpFrequency, itemNumber);
    }

    @ApiOperation("搜索用户成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/score/search")
    public ResultData scoreSearch(String userName){
        System.out.println(userName);
        return scoreService.searchScore("%"+userName+"%");
    }

    @ApiOperation("删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @GetMapping("/score/delete")
    public ResultData deleteScore(@RequestParam int id,@RequestParam(defaultValue = "1") int pageNo) {
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return scoreService.deleteScore(id);
    }

    @ApiOperation("添加成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "rolename", value = "角色", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userCode", value = "账号", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "jumpFrequency", value = "跳跃高度", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "itemNumber", value = "拾取道具数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @GetMapping("/score/add")
    public ResultData addScore(String userCode,String userName, String rolename, Integer jumpFrequency, Integer itemNumber) {
        return scoreService.addScore(userCode, userName , rolename, jumpFrequency, itemNumber);
    }

    @ApiOperation("成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/score")
        public ResultData score(@RequestParam(required = false, defaultValue = "1") int pageNo){
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return scoreService.GetAllGrades(0);
    }

    @ApiOperation("成绩排行")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/rank")
    public ResultData stock(@RequestParam(required = false, defaultValue = "1") int pageNo){
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return scoreService.GetAllGrades(1);
    }
}
