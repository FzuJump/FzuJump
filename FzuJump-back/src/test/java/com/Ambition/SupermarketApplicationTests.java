package com.Ambition;

import com.Ambition.mapper.PermissionMapper;
import com.Ambition.pojo.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SupermarketApplicationTests {


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
        /**
         * 数字金额大写转换 先写个完整的然后将如零拾替换成零
         *
         * @param n 数字
         * @return 中文大写数字
         */
            double n = 123456;
            String[] fraction = {"角", "分"};
            String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
            String[][] unit = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

            String head = n < 0 ? "负" : "";
            n = Math.abs(n);

            String s = "";
            for (int i = 0; i < fraction.length; i++) {
                s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
            }
            if (s.length() < 1) {
                s = "整";
            }
            int integerPart = (int) Math.floor(n);

            for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
                String p = "";
                for (int j = 0; j < unit[1].length && n > 0; j++) {
                    p = digit[integerPart % 10] + unit[1][j] + p;
                    integerPart = integerPart / 10;
                }
                s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
            }

            System.out.println(head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整"));
        }

}
