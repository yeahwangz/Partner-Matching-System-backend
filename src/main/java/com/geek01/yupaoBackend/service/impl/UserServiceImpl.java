package com.geek01.yupaoBackend.service.impl;

import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.constant.UserConstant;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.exception.ErrorException;
import com.geek01.yupaoBackend.mapper.UserMapper;
import com.geek01.yupaoBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.geek01.yupaoBackend.constant.UserConstant.USER_LOGIN_INFO;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl /*mp用法 extends ServiceImpl<UserMapper, User>*/ implements UserService {

    private final UserMapper userMapper;

    private static final String SALT = "01geek";

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
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setTags(originUser.getTags());
        safetyUser.setProfile(originUser.getProfile());
        return safetyUser;
    }

    /**
     * 注册普通用户，获取普通用户id
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    @Transactional
    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1L;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1L;
        }
        // 确认普通用户名并没有被使用过
        User user = userMapper.getUserByUserAccount(userAccount);
        if (user != null) {
            return -1L;
        }
        User newUser = new User();
        newUser.setUserAccount(userAccount);
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        newUser.setUserPassword(encryptPassword);
        try {
            userMapper.addNewUserAndReturnUserId(newUser);
        }catch (Exception e){
            log.error("新增普通用户失败",e);
            throw new ErrorException(ErrorCode.INSERTSQL_ERROR);
        }
        return newUser.getId();
    }

    /**
     * 普通用户登录
     * @param userAccount
     * @param userPassword
     * @param httpServletRequest
     * @return
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest) {
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new ErrorException(ErrorCode.PARAMS_ERROR);
        }
        //根据用户账号名搜索用户
        User dbUser = userMapper.getUserByUserAccount(userAccount);
        // 用户不存在
        if (dbUser == null) {
            throw new ErrorException(ErrorCode.NO_USEREXIST);
        }
        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 密码错误
        if (!encryptPassword.equals(dbUser.getUserPassword())) {
            throw new ErrorException(ErrorCode.LOGIN_PASSWORD_ERROR);
        }
        User returnUser = getSafetyUser(dbUser);
        httpServletRequest.getSession().setAttribute(USER_LOGIN_INFO, returnUser);
        return returnUser;
    }

    /**
     * 注销当前用户
     * @param httpServletRequest
     * @return
     */
    @Override
    public Integer userLogout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(USER_LOGIN_INFO);
        return 1;
    }

    /**
     * 根据cookie确定用户，并修改相应用户信息
     * @param request
     * @param newUserInfo
     * @return
     */
    @Transactional
    @Override
    public User editUserInfoByCookie(HttpServletRequest request, User newUserInfo) {
        User oldUserInfo = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        Long id = oldUserInfo.getId();
        //用户id和用户想要修改的id不同，越权
        if ( newUserInfo.getId() == null || !newUserInfo.getId().equals(id) ){
            throw new ErrorException(ErrorCode.NO_AUTH);
        }
        userMapper.editUserInfoByCookie(newUserInfo);
        User noSafetyUserInfo = userMapper.getUserById(newUserInfo.getId());
        return getSafetyUser(noSafetyUserInfo);
    }

    /**
     * 根据cookie获取返回安全用户信息
     * @param request
     * @return
     */
    @Override
    public User getSaftyUserInfoByCookie(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        return getSafetyUser(user);
    }
}
