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
}
