package com.Ambition.controller;

import com.Ambition.Utils.Code;
import com.Ambition.Utils.MailerServiceImpl;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.User;
import com.Ambition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.lang.ref.PhantomReference;

@Api(tags = "邮件发送")
@RestController
public class EmailController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @ApiOperation("发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "value", value = "日志内容", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Integer", dataTypeClass = Integer.class),
    })
    @PostMapping("/email")
    public ResultData logout(String value ,Integer userId){
        ResultData resultData = new ResultData();
        User user = userMapper.GetUserBy(userId, null, null);
        if(user.getEmail() != null){
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("发送成功");
            userService.Email(value,userId,user.getEmail());
        }
        else{
            resultData.setCode(Code.FALISE);
            resultData.setMsg("发送失败");
        }
        return resultData;
    }
}
