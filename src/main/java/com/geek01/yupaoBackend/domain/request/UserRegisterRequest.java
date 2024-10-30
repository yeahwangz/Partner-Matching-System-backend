package com.geek01.yupaoBackend.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 6554865L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;
}
