package com.Ambition.controller;

import com.Ambition.Utils.Constant;
import com.Ambition.dto.ResultData;
import com.Ambition.service.LogService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "日志消息")
@RestController
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation("显示全部日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @GetMapping("/log")
    public Map change(@RequestParam(defaultValue = "1")int pageNo){
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return logService.getAllLog();
    }

    @ApiOperation("添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "message", value = "页数", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/log/add")
    public ResultData addLog(String message) {
        ResultData resultData = logService.addLog(message);
        PageHelper.startPage(1, Constant.LIMIT);
        Map<String, Object> allLog = logService.getAllLog();
        resultData.setData(allLog);
        return resultData;
    }

    @ApiOperation("删除日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "userId", value = "日志id", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/log/delete")
    public ResultData removeUser(@RequestParam Integer userId, @RequestParam Integer pageNo) {
        System.out.println("==========>");
        ResultData resultData1 = logService.deleteLog(userId);
        PageHelper.startPage(pageNo, Constant.LIMIT);
        Map<String, Object> allLog = logService.getAllLog();
        resultData1.setData(allLog);
        return resultData1;
    }
}
