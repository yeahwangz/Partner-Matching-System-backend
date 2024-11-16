package com.geek01.yupaoBackend.service;

import com.geek01.yupaoBackend.domain.User;

import java.util.List;

public interface TestService {
    /**
     * 往sql中批量插入用户
     * @param user
     */
    void insertUser(User user);

    void insertBatchMybatis(List<User> users);
}
