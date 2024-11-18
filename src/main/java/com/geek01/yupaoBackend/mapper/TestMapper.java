package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    /**
     * 插入单个用户user
     * @param user
     */
    void insertUser(User user);

    /**
     * 往sql中批量插入用户
     * @param users
     */
    void insertBatchMybatis(List<User> users);

    /**
     * 根据用户id从user表删除用户
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 除用户，需根据情况自己修改
     */
    void deleteUser();

}
