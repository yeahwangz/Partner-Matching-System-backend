package com.geek01.yupaoBackend;

import com.geek01.yupaoBackend.domain.User;
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

    @Test
    void contextLoads() {
    }

    @Test
    public void testForSearchUsersByTags(){
        List<String> tagsList = Arrays.asList("java", "c++");
        List<User> userList = userService.searchUsersByTags(tagsList);
        Assert.assertNotNull(userList);
    }
}
