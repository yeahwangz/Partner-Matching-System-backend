package com.geek01.yupaoBackend.controller;

import com.geek01.yupaoBackend.common.BaseResponse;
import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.domain.User;
import com.geek01.yupaoBackend.service.UserService;
import com.geek01.yupaoBackend.utils.ResultUtils;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
