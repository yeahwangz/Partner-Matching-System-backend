package com.geek01.yupaoBackend.domain.po;

import lombok.Data;

@Data
public class TeamSimilarPO {
    private Long teamId;

    private String tags;

    private Long similarity;
}
