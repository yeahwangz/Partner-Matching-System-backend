package com.geek01.yupaoBackend.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 推荐的心动用户 <= 100位
 */
@Data
public class UserToRecommendVO implements Serializable {
    @Serial //jdk 14引入 可省略
    private static final long serialVersionUID = 554351L;

    private Long id;

    private String userName;

    private String tags;

    private String userAccount;

    private String avatarUrl;

    private Integer gender;

    private String phone;

    private String email;

    private String profile;
}
