package com.Ambition.controller;

import com.Ambition.Utils.Code;
import com.Ambition.config.SpringSecurityConfig;
import com.Ambition.dto.ResultData;
import com.Ambition.mapper.UserMapper;
import com.Ambition.pojo.User;
import com.Ambition.service.LoginServcie;
import com.Ambition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Api(tags = "登录登出")
@RestController
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private LoginServcie loginServcie;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @ApiOperation("登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/tologin")
    public ResultData login(HttpSession session, @RequestParam()String username, @RequestParam()String password){
        /*登录验证数据*/
        User user = new User();
        user.setUserCode(username);
        user.setPassword(password);
        ResultData login = loginServcie.login(user);
        if(login.getCode() == 200){
            User user1 = userService.GetUserBy(username, bCryptPasswordEncoder.encode(password));
            session.setAttribute("user",user1);
        }
        return login;
    }

    @ApiOperation("注销")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "登录令牌", dataType = "String", dataTypeClass = String.class,required = true)
    })
    @GetMapping("/toLoginOut")
    public ResultData logout(){
        return loginServcie.logout();
    }


    @GetMapping("/unLogin")
    public String unLogin(){
        return "未登录";
    }

    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "usercode", value = "用户账号", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query",name = "password", value = "用户密码", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/register")
    public ResultData register(String userCode,String password){
        ResultData resultData = new ResultData();
        User user = userMapper.GetUserBy(null, userCode, null);
        if(user != null){
            resultData.setCode(Code.FALISE);
            resultData.setMsg("用户名重复");
        }
        else{
            User user1 = new User();
            user1.setUserCode(userCode);
            user1.setUserName("匿名用户");
            user1.setRoleId(7);
            user1.setPassword(bCryptPasswordEncoder.encode(password));
            userMapper.addUser1(user1);
            resultData.setCode(Code.SUCCESS);
            resultData.setMsg("注册成功");
        }
        return resultData;
    }

}
