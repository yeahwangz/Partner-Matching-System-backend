package com.geek01.yupaoBackend.mapper;

import com.geek01.yupaoBackend.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper /*mp用法 extends BaseMapper<User>*/ {

    /**
     * 根据标签搜索用户
     * @param tagsNameList
     * @return
     */
    List<User> selectUsersByTagsName(List<String> tagsNameList);
}
