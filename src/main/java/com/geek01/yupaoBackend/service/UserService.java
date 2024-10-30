package com.geek01.yupaoBackend.service;

import com.geek01.yupaoBackend.domain.User;

import java.util.List;

public interface UserService /*extends IService<User>*/ {
    /**
     * 根据标签名搜索用户信息
     * @param tagsNameList
     * @return
     */
    List<User> searchUsersByTags(List<String> tagsNameList);

    /**
     * 获取安全用户信息
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 注册普通用户，获取普通用户id
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword);
}
