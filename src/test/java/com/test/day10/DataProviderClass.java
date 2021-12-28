package com.test.day10;

import org.testng.annotations.DataProvider;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/3 22:19
 */
public class DataProviderClass {

    @DataProvider
    public static Object[] data(){
        Object[] datas = {"a","b","c"};
        return datas;
    }
}
