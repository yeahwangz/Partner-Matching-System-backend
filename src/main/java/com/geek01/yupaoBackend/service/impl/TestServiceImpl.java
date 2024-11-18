package com.geek01.yupaoBackend.service.impl;

import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.mapper.TestMapper;
import com.geek01.yupaoBackend.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    /**
     * 往sql中批量插入用户
     * @param user
     */
    @Override
    @Transactional
    public void insertUser(User user) {
        testMapper.insertUser(user);
    }

    @Override
    @Transactional
    public void insertBatchMybatis(List<User> users) {
        testMapper.insertBatchMybatis(users);
    }

    @Override
    public void deleteUserById(Integer id) {
        testMapper.deleteUserById(id);
    }

    /**
     * 删除用户，需根据情况自己修改
     */
    @Override
    public void deleteUser() {
        testMapper.deleteUser();
    }


}
