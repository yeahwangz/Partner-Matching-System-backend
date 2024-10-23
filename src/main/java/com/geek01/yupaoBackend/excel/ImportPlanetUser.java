package com.geek01.yupaoBackend.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ImportPlanetUser {
    public static void main(String[] args) {
        String fileName = "src/main/resources/prodExcel.xlsx";
        List<PlanetUserInfo> lists = EasyExcel.read(fileName).head(PlanetUserInfo.class).sheet().doReadSync();
        System.out.println("总人数为："+lists.size());
        lists.stream()
                .filter(list -> StringUtils.isNotBlank(list.getUserName()))
                .collect(Collectors.groupingBy(planetUserInfo -> planetUserInfo.getUserName()));
    }
}
