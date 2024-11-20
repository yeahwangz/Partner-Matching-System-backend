package com.geek01.yupaoBackend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    /**
     * 确保序列化和反序列化过程中类的兼容性
     */
    private static final long serialVersionUID = 8795666L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;
}
