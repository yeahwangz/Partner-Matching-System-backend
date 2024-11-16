package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    /**
     * 往sql中批量插入用户
     * @param user
     */
    void insertUser(User user);

    void insertBatchMybatis(List<User> users);
}
