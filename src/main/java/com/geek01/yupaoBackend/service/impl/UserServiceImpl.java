package com.geek01.yupaoBackend.service.impl;

import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.exception.ErrorException;
import com.geek01.yupaoBackend.mapper.UserMapper;
import com.geek01.yupaoBackend.service.UserService;
import com.geek01.yupaoBackend.utils.ResultUtils;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl /*mp用法 extends ServiceImpl<UserMapper, User>*/ implements UserService {

    private final UserMapper userMapper;

    /**
     * 根据标签搜索用户
     *
     * @param tagsNameList 用户拥有的标签
     * @return 用户
     */
    @Override
    public List<User> searchUsersByTags(List<String> tagsNameList) {

        /*mp用法  QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        for(String tagName : tagsNameList){
            queryWrapper.like("tags", tagName);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList.stream().map(this::getSafetyUser).collect(Collectors.toList());*/

        //mybatis用法
        List<User> userList = userMapper.selectUsersByTagsName(tagsNameList);
        List<User> userListTar = new ArrayList<>();
        for (User user : userList) {
            userListTar.add(getSafetyUser(user));
        }
        return userListTar;
    }

    /**
     * 获取安全用户信息
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setTags(originUser.getTags());
        safetyUser.setProfile(originUser.getProfile());
        return safetyUser;
    }
}
