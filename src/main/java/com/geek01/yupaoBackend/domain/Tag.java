package com.geek01.yupaoBackend.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
/*mp @TableName(value = "tag")*/
public class Tag implements Serializable {
    private Long id;

    private String tagName;

    private Long userId;

    private Long parentId;

    private Integer isParent;

    private Date createTime;

    private Date updateTime;

    /*mp @TableLogic*/
    private Integer idDelete;

    /*mp @TableField(exist = false)*/
    private static final long serialVersionUID = 459848L;
}
