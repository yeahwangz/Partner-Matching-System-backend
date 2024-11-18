package com.geek01.yupaoBackend;

import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.service.impl.TestServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class sqlUserInsert {

    @Resource
    private TestServiceImpl testServiceImpl;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 往sql中批量插入用户
     * 方法一：暴力直接一条条插入数据库
     * 插入20000条数据耗时：66.194秒 （使用了springboot默认连接池）
     */
    @Test
    void insertUser1(){
        User user = new User();
        Date beforeDate = new Date();
        for (int i = 20000; i < 40000; i++) {
            user.setUserAccount(String.valueOf(i));
            user.setUserPassword(String.valueOf(123456));
            testServiceImpl.insertUser(user);
        }
        Date afterDate = new Date();
        System.out.println("共耗时(ms)：" + (afterDate.getTime() - beforeDate.getTime()) );
    }

    /**
     * 往sql中批量插入用户
     * 方法二：使用druid连接池
     * 插入20000条数据耗时：68秒
     */
    @Test
    void insertUser2(){
        User user = new User();
        Date beforeDate = new Date();
        for (int i = 60000; i < 80000; i++) {
            user.setUserAccount(String.valueOf(i));
            user.setUserPassword(String.valueOf(123456));
            testServiceImpl.insertUser(user);
        }
        Date afterDate = new Date();
        System.out.println("共耗时(ms)：" + (afterDate.getTime() - beforeDate.getTime()) );
    }

    /**
     * 往sql中批量插入用户
     * 方法三：利用mybatis的foreach来实现循环插入,每10条一次插入
     * 插入20000条数据耗时：7.927秒
     */
    @Test
    void insertUser3(){
        Date beforeDate = new Date();
        for (int i = 0; i < 2000 ; i++) {
            List<User> users = new ArrayList<>();
            for (int j = 0+10*i; j < 0+10*(i+1); j++) {
                User user = new User();
                user.setUserAccount(String.valueOf(j));
                user.setUserPassword(String.valueOf(123456));
                //插入的是对象user的地址
                users.add(user);
            }
            testServiceImpl.insertBatchMybatis(users);
        }
        Date afterDate = new Date();
        System.out.println("共耗时(ms)：" + (afterDate.getTime() - beforeDate.getTime()) );
    }

    @Test
    void otherTest() throws InterruptedException {
        Date beforeDate = new Date();
        System.out.println(beforeDate);
        Thread.sleep(5000);
        Date afterDate = new Date();
        System.out.println(afterDate.getTime());
        System.out.println(afterDate.getTime() - beforeDate.getTime());

    }
}
