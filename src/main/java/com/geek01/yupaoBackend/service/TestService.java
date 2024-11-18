package com.geek01.yupaoBackend.service;

import com.geek01.yupaoBackend.domain.User;

import java.util.List;

public interface TestService {
    /**
     * 往sql中批量插入用户
     * @param user
     */
    void insertUser(User user);

    /**
     * sql批量插入
     * @param users
     */
    void insertBatchMybatis(List<User> users);

    /**
     * 根据用户id从user表删除用户
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 删除用户，需根据情况自己修改
     */
    void deleteUser();
}
