package com.geek01.yupaoBackend.excel;

import com.alibaba.excel.EasyExcel;

import java.util.List;
import java.util.Map;

public class ReadExcel {
    public static void main(String[] args) {
        String fileName = "src/main/resources/testExcel.xlsx";
        //ReadBylistener(fileName);
        synchronousRead(fileName);
    }

    /**
     * 同步的返回，不推荐使用，如果数据量大会把数据放到内存里面
     * @param fileName
     */
    private static void synchronousRead(String fileName) {

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<PlanetUserInfo> list = EasyExcel.read(fileName).head(PlanetUserInfo.class).sheet().doReadSync();
        for (PlanetUserInfo planetUserInfo : list) {
            System.out.println(planetUserInfo);
        }
    }

    /**
     * 使用监听器读
     * @param fileName
     */
    private static void ReadBylistener(String fileName) {
        // 写法1：JDK8+ ,不用额外写一个PlanetUserInfoListener
        // since: 3.0.0-beta1
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, PlanetUserInfo.class, new TableListener()).sheet().doRead();
    }


}
