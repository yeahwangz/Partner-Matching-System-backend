package com.geek01.yupaoBackend.service;

import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.domain.vo.UserToRecommendVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 普通用户登录
     * @param userAccount
     * @param userPassword
     * @param httpServletRequest
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest);

    /**
     * 注销当前用户
     * @param httpServletRequest
     * @return
     */
    Integer userLogout(HttpServletRequest httpServletRequest);

    /**
     * 根据cookie确定用户，并修改相应用户信息
     * @param request
     * @param newUserInfo
     * @return
     */
    User editUserInfoByCookie(HttpServletRequest request, User newUserInfo);

    /**
     * 根据cookie获取返回安全用户信息
     * @param request
     * @return
     */
    User getSaftyUserInfoByCookie(HttpServletRequest request);

    /**
     * 存储用户头像并返回给前端
     * @param httpServletRequest
     * @param file
     * @return
     */
    String uploadImage(HttpServletRequest httpServletRequest, MultipartFile file);

    /**
     * 根据用户的标签进行用户推荐 选取前100条最匹配的
     * @param request
     * @return
     */
    List<UserToRecommendVO> getRecommendUserList(HttpServletRequest request);

    /**
     * 根据用户的标签进行普通用户推荐
     * @return
     */
    List<UserToRecommendVO> getNormalRecommendUserList();

    /**
     * 计算数据库中前端n条数据一页共可以显示m页，返回m
     * @param everyPageSize
     * @return
     */
    Long getNormalUserPageNum(Integer everyPageSize);

    /**
     * 根据前端发送的页码数和每页数据量获取该页的用户
     *
     * @param nowPage
     * @param everyPageSize
     * @return
     */
    List<UserToRecommendVO> getNormalUserOnePage(Long nowPage, Integer everyPageSize);
}
