package com.geek01.yupaoBackend.domain.po;

import lombok.Data;

@Data
public class UserSimilarPO {
    private Long userId;

    private String tags;

    private Long similarity;
}
