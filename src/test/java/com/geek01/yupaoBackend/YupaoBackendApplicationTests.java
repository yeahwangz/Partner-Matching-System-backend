package com.geek01.yupaoBackend;

import com.geek01.yupaoBackend.algorithm.LevenshteinDistance;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.mapper.TestMapper;
import com.geek01.yupaoBackend.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class YupaoBackendApplicationTests {
    @Resource
    private UserService userService;

    @Resource
    private LevenshteinDistance levenshteinDistance;

    @Resource
    private TestMapper testMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testForSearchUsersByTags(){
        List<String> tagsList = Arrays.asList("java", "c++");
        List<User> userList = userService.searchUsersByTags(tagsList);
        Assert.assertNotNull(userList);
    }

    /**
     * 编辑距离算法
     */
    @Test
    void distance(){
        String s = "[\"大四\",\"女\",\"c++\",\"java\"]";
        String t = "[\"大二\",\"女\",\"c++\"]";
        System.out.println(levenshteinDistance.getDistance(s,t));
    }

    /**
     * 其他一些测试
     */
    @Test
    void others(){
/*        User user = new User();
        user.setUserAccount("触发器插入测试1");
        user.setUserPassword("123456");
        testMapper.insertUser(user);*/

        /*testMapper.deleteUserById(100067);*/

        testMapper.deleteUser();
    }


}
