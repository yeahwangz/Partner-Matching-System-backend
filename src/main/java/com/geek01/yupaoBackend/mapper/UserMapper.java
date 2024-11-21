package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.domain.dto.TeamDTO;
import com.geek01.yupaoBackend.domain.po.TeamPO;
import com.geek01.yupaoBackend.domain.po.TeamSimilarPO;
import com.geek01.yupaoBackend.domain.po.UserSimilarPO;
import com.geek01.yupaoBackend.domain.vo.TeamVO;
import com.geek01.yupaoBackend.domain.vo.UserToRecommendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;
import java.util.Map;

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
     * 获取最终推荐的<=maxCapacity个用户信息
     * @param userSimilarPOList
     * @return
     */
    List<UserToRecommendVO> getUserToRecommendVO(List<UserSimilarPO> userSimilarPOList);

    /**
     * 计算数据库中前端n条数据一页共可以显示m页，返回m
     * @return
     */
    Long getAllUserNum();

    /**
     * 根据前端发送的页码数和每页数据量获取该页的用户
     * @param map
     * @return
     */
    List<UserToRecommendVO> getNormalUserOnePage(Map<String,Object> map);

    /**
     * 创建新队伍
     *
     * @param teamPO
     */
     void createNewTeam(TeamPO teamPO);

    /**
     * 根据team的id获得team
     * @param teamId
     * @return
     */
    TeamPO getTeamById(Long teamId);

    /**
     * 计算数据库中前端team，n条数据一页共可以显示m页，返回m
     * @return
     */
    Long getAllTeamNum();

    /**
     * 根据前端发送的页码数和每页数据量获取该页的team
     * @param params
     * @return
     */
    List<TeamVO> getNormalTeamOnePage(Map<String, Object> params);

    /**
     * 根据用户的标签进行用户推荐 选取前100条最匹配的
     * @param id
     * @return
     */
    Cursor<TeamSimilarPO> selectTeamsHaveTagsByCursor(Long id);

    /**
     * 获取最终推荐的<=maxCapacity个队伍信息
     * @param teamSimilarPOList
     * @return
     */
    List<TeamVO> getTeamVO(List<TeamSimilarPO> teamSimilarPOList);

    /**
     * 存储队伍头像并返回给前端
     * @param imageUrl
     * @param teamId
     */
    void updateTeamAvatarUrl(@Param("imageUrl") String imageUrl, @Param("teamId") Long teamId);

    /**
     * 获取用户已经加入的队伍
     * @param userId
     * @return
     */
    List<Long> getTeamIdListByUserId(Long userId);

    /**
     * 加入队伍
     * @param userId
     * @param teamId
     */
    void joinTeam(@Param("userId") Long userId,@Param("teamId") Long teamId);

    /**
     * 获取当前队伍队长id
     * @param teamId
     * @return
     */
    Long getCurrentTeamLeaderId(Long teamId);

    /**
     * 修改队伍
     * @param teamId
     * @param teamDTO
     */
    void updateTeam(@Param("teamId") Long teamId,@Param("teamDTO") TeamDTO teamDTO);

    /**
     * 删除队伍
     * @param teamId
     */
    void deleteTeam(Long teamId);

    /**
     * 转让队长
     * @param teamId
     * @param futureLeaderId
     */
    void changeLeader(@Param("teamId") Long teamId, @Param("futureLeaderId") Long futureLeaderId);
}
