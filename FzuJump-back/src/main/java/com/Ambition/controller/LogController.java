package com.Ambition.controller;

import com.Ambition.Utils.Constant;
import com.Ambition.service.LogService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @GetMapping("/log")
    public Map change(@RequestParam(defaultValue = "1")int pageNo){
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return logService.getAllLog();
    }
}
