package com.geek01.yupaoBackend.controller;

import com.geek01.yupaoBackend.common.BaseResponse;
import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.domain.request.UserRegisterRequest;
import com.geek01.yupaoBackend.service.UserService;
import com.geek01.yupaoBackend.utils.ResultUtils;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
     * @param userRegisterRequest
     * @return
     */
    @ApiOperation("注册新普通用户")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR,1L);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,1L);
        }
        Long userId = userService.userRegister(userAccount,userPassword,checkPassword);
        return ResultUtils.success(userId);
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
            return ResultUtils.error(ErrorCode.NULL_ERROR,new ArrayList<User>());
        }
        return ResultUtils.success(userService.searchUsersByTags(tagsList));
    }
}
