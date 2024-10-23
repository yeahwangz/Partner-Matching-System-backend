package com.geek01.yupaoBackend.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 星球表格用户信息
 */
@Data
public class PlanetUserInfo {

    /**
     * id
     */
    @ExcelProperty("成员编号")
    private String planetCode;

    /**
     * 用户昵称
     */
    @ExcelProperty("成员昵称")
    private String userName;

}