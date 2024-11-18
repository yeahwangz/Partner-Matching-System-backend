package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.domain.po.UserSimilarPO;
import com.geek01.yupaoBackend.domain.vo.UserToRecommendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;

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

    /**
     * 根据用户id搜索用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据id修改数据库中用户头像
     * @param user
     */
    void updateAvatarUrlByUser(User user);

    /**
     * 使用流式查询搜索所有用户
     * @return
     */
    Cursor<UserSimilarPO> selectUsersHaveTagsByCursor(Long userId);

    /**
     * 获得user表中用户信息条数
     * @return
     */
    Integer queryCountUsersHaveTags();

    /**
     * 获取最终推荐的maxCapacity个用户信息
     * @param userSimilarPOList
     * @return
     */
    List<UserToRecommendVO> getUserToRecommendVO(List<UserSimilarPO> userSimilarPOList);
}
