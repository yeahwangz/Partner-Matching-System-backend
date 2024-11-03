package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper /*mp用法 extends BaseMapper<User>*/ {

    /**
     * 根据标签搜索用户
     * @param tagsNameList
     * @return
     */
    List<User> selectUsersByTagsName(List<String> tagsNameList);

    /**
     * 根据用户账号名搜索用户
     * @param userAccount
     * @return
     */
    User getUserByUserAccount(String userAccount);

    /**
     * 新增用户并返回用户id
     * @param newUser
     */
    void addNewUserAndReturnUserId(User newUser);

    /**
     * 根据cookie确定用户，并修改相应用户信息
     * @param newUserInfo
     */
    void editUserInfoByCookie(User newUserInfo);
}
