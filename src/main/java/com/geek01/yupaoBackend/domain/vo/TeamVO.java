package com.geek01.yupaoBackend.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TeamVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5565661L;

    private Long id;

    private String teamName;

    private String profile;

    private String tags;

    private String avatarUrl;

    private String currentMember;

    private Integer maxMemberNum;
}
