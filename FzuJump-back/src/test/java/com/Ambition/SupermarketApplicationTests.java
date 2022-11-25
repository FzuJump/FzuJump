package com.Ambition;

import com.Ambition.mapper.PermissionMapper;
import com.Ambition.pojo.Permission;
import com.Ambition.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SupermarketApplicationTests {

    @Resource
    private ScoreService scoreService;

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
    @Test
    void test(){
        List<Permission> permissions = permissionMapper.GetPermissionByRoleId(1);
        System.out.println(permissions);
    }

    @Test
    void mytest(){
        Map<String, Object> map = scoreService.appGetAllScore(1);
        System.out.println(map);
    }

}
