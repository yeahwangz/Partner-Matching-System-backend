package com.geek01.yupaoBackend.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
/*@TableName(value = "user")*/
public class User implements Serializable {
    /*@TableField(exist = false)*/
    private static final long serialVersionUID = 1L;

    private String userName;

    private Long id;

    private String tags;

    private String userAccount;

    private String avatarUrl;

    private Integer gender;

    private String userPassword;

    private String phone;

    private String email;

    private Integer userStatus;

    private Date createTime;

    private Date updateTime;

    /*@TableLogic*/
    private Integer isDelete;

    private Integer userRole;

    private String planetCode;
}
