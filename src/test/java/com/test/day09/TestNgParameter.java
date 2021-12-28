package com.test.day09;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/2 20:45
 */
public class TestNgParameter {

    @Test
    @Parameters("type")
    public void testParameter(String type){
        System.out.println("传参："+type);
    }
}
