package com.geek01.yupaoBackend.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class TeamPO {

    private Long id;

    private String teamName;

    private String profile;

    private String tags;

    private String avatarUrl;

    private String currentMember;

    private String historyMember;

    private String historyLeader;

    private Integer maxMemberNum;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

}
