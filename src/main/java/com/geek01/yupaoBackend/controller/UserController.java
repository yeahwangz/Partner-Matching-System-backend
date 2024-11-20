package com.geek01.yupaoBackend.controller;

import com.geek01.yupaoBackend.common.BaseResponse;
import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.common.ReturnType;
import com.geek01.yupaoBackend.constant.UserConstant;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.domain.dto.TeamDTO;
import com.geek01.yupaoBackend.domain.dto.UserLoginDTO;
import com.geek01.yupaoBackend.domain.dto.UserRegisterDTO;
import com.geek01.yupaoBackend.domain.vo.TeamVO;
import com.geek01.yupaoBackend.domain.vo.UserToRecommendVO;
import com.geek01.yupaoBackend.mapper.UserMapper;
import com.geek01.yupaoBackend.service.UserService;
import com.geek01.yupaoBackend.utils.ResultUtils;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONString;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户相关接口")
public class UserController {

    private final UserService userService;

    /**
     * 注册用户，获取普通用户id
     * @param userRegisterDTO
     * @return
     */
    @ApiOperation("注册新普通用户")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("注册新普通用户：{}", userRegisterDTO);
        if (userRegisterDTO == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR,1L);
        }
        String userAccount = userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,1L);
        }
        Long userId = userService.userRegister(userAccount,userPassword,checkPassword);
        return ResultUtils.success(userId);
    }

    @ApiOperation("普通用户登录")
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginDTO userLoginDTO,
                                        HttpServletRequest httpServletRequest) {
        log.info("普通用户登录：{}", userLoginDTO);
        if (userLoginDTO == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR,new User());
        }
        String userAccount = userLoginDTO.getUserAccount();
        String userPassword = userLoginDTO.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,new User());
        }
        return ResultUtils.success(userService.userLogin(userAccount,userPassword,httpServletRequest));
    }

    /**
     * 注销当前用户
     * @param httpServletRequest
     * @return
     */
    @ApiOperation("注销当前用户")
    @GetMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest httpServletRequest) {
        log.info("注销当前用户：{}",httpServletRequest);
        if (httpServletRequest == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR,1);
        }
        return ResultUtils.success(userService.userLogout(httpServletRequest));
    }

    /**
     * 根据标签查询用户
     * @param tagsList
     * @return
     */
    @ApiOperation("根据标签查询用户")
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> getUsersByTags(@RequestParam(required = false) List<String> tagsList) {
    log.info("获取的标签：{}", tagsList);
    if(CollectionUtils.isEmpty(tagsList)) {
        return ResultUtils.error(ErrorCode.NULL_ERROR,new ArrayList<>());
    }
    return ResultUtils.success(userService.searchUsersByTags(tagsList));
    }

    /**
     * 根据cookie确定用户，并修改相应用户信息
     * @param request
     * @param newUserInfo
     * @return
     */
    @PostMapping("/edit/userInfo")
    @ApiOperation("根据cookie确定用户，并修改相应用户信息")
    public BaseResponse<User> editUserInfoByCookie(HttpServletRequest request,
                                                      @RequestBody User newUserInfo) {
        User returnSafetyUserInfo = userService.editUserInfoByCookie(request,newUserInfo);
        return ResultUtils.success(returnSafetyUserInfo);
    }

    /**
     * 根据cookie获取返回安全用户信息
     * @param request
     * @return
     */
    @GetMapping("/search/userInfo")
    @ApiOperation("根据cookie获取返回安全用户信息")
    public BaseResponse<User> getSaftyUserInfoByCookie(HttpServletRequest request) {
        User safetyUser = userService.getSaftyUserInfoByCookie(request);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 存储用户头像并返回给前端
     * @param request
     * @param file
     * @return
     */
    @PostMapping(value = "/upload/userImage",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("存储用户头像并返回给前端")
    public BaseResponse<String> uploadUserImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file == null) {
            return ResultUtils.error(ReturnType.StringType,ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(userService.uploadUserImage(request,file));
    }

    /**
     * 根据用户的标签进行用户推荐 选取前100条最匹配的
     * @param request
     * @return
     */
    @GetMapping("/recommend/loveUser")
    @ApiOperation("根据用户的标签进行心动用户推荐")
    public BaseResponse<List<UserToRecommendVO>> getLoveRecommendUserList(HttpServletRequest request){
        return ResultUtils.success(userService.getRecommendUserList(request));
    }

    /**
     * 计算数据库中前端user，n条数据一页共可以显示m页，返回m
     * @param everyPageSize
     * @return
     */
    @GetMapping("/recommend/normalUserPageNum")
    @ApiOperation("计算数据库中前端user，n条数据一页共可以显示m页，返回m")
    public BaseResponse<Long> getNormalUserPageNum(@RequestParam Integer everyPageSize){
        if (everyPageSize < 1) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,1L);
        }
        return ResultUtils.success(userService.getNormalUserPageNum(everyPageSize));
    }

    /**
     * 根据前端发送的页码数和每页数据量获取该页的用户
     * @param nowPage
     * @param everyPageSize
     * @return
     */
    @GetMapping("/recommend/normalUserOnePage")
    @ApiOperation("根据前端发送的页码数和每页数据量获取该页的用户")
    public BaseResponse<List<UserToRecommendVO>> getNormalUserOnePage(@RequestParam Long nowPage
            ,@RequestParam Integer everyPageSize){
        if ( nowPage < 1 || everyPageSize < 1) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,new ArrayList<>());
        }
        return ResultUtils.success(userService.getNormalUserOnePage(nowPage,everyPageSize));
    }

    /**
     * 创建新队伍
     * @param request
     * @param teamDTO
     * @return
     */ 
    @PostMapping("/createNewTeam")
    @ApiOperation("创建新队伍")
    public BaseResponse<TeamVO> createNewTeam(HttpServletRequest request, @RequestBody TeamDTO teamDTO){
        if (teamDTO == null) {
            ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(userService.createNewTeam(request,teamDTO));
    }

    /**
     * 计算数据库中前端team, n条数据一页共可以显示m页，返回m
     * @param everyPageSize
     * @return
     */
    @GetMapping("/recommend/normalTeamPageNum")
    @ApiOperation("计算数据库中前端team, n条数据一页共可以显示m页，返回m")
    public BaseResponse<Long> getNormalTeamPageNum(@RequestParam Integer everyPageSize){
        if (everyPageSize < 1) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,1L);
        }
        return ResultUtils.success(userService.getNormalTeamPageNum(everyPageSize));
    }

    /**
     * 根据前端发送的页码数和每页数据量获取该页的team
     * @param nowPage
     * @param everyPageSize
     * @return
     */
    @GetMapping("/recommend/normalTeamOnePage")
    @ApiOperation("根据前端发送的页码数和每页数据量获取该页的team")
    public BaseResponse<List<TeamVO>> getNormalTeamOnePage(@RequestParam Long nowPage
            ,@RequestParam Integer everyPageSize){
        if ( nowPage < 1 || everyPageSize < 1) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,new ArrayList<>());
        }
        return ResultUtils.success(userService.getNormalTeamOnePage(nowPage,everyPageSize));
    }

    /**
     * 根据用户的标签进行队伍推荐,选取前100条最匹配的
     * @param request
     * @return
     */
    @GetMapping("/recommend/loveTeam")
    @ApiOperation("根据用户的标签进行心动队伍推荐")
    public BaseResponse<List<TeamVO>> getLoveRecommendTeamList(HttpServletRequest request){
        return ResultUtils.success(userService.getRecommendTeamList(request));
    }

    /**
     * 存储队伍头像并返回给前端
     * @param request
     * @param file
     * @return
     */
    @PostMapping( value = "/upload/teamImage",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("存储队伍头像并返回给前端")
    public BaseResponse<String> uploadTeamImage(HttpServletRequest request,
                                                @RequestParam("file") MultipartFile file,
                                                @RequestParam("teamId") Long teamId) {
        if (file == null || teamId == null || teamId <= 0) {
            return ResultUtils.error(ReturnType.StringType,ErrorCode.NULL_ERROR);
        }
        User loginUser = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        List<Long> teamIdList = userService.getTeamIdListByUserId(loginUser.getId());
        if (!teamIdList.contains(teamId)){
            return ResultUtils.error(ReturnType.StringType,ErrorCode.NO_AUTH);
        }
        return ResultUtils.success(userService.uploadTeamImage(file,teamId));
    }

    /**
     * 加入队伍
     * @param request
     * @param teamId
     * @return
     */
    @GetMapping("/joinTeam")
    @ApiOperation("加入队伍")
    public BaseResponse<Boolean> joinTeam(HttpServletRequest request, Long teamId){
        if (teamId == null || teamId <= 0) {
            ResultUtils.error(ReturnType.StringType,ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(userService.joinTeam(request,teamId));
    }
}
