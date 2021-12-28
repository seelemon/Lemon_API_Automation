package com.test.day09;

import org.testng.annotations.*;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/2 15:17
 */
public class TestNgDemo2 {
    @Test
    public void test(){
        System.out.println("TestNgDemo2.test");
    }

    @Test
    public void test2(){
        System.out.println("TestNgDemo2.test2");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("TestNgDemo2.beforeClass");
    }

    @AfterClass
    public void afterclass(){
        System.out.println("TestNgDemo2.afterClass");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("TestNgDemo2.beforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("TestNgDemo2.afterSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("TestNgDemo2.beforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("TestNgDemo2.afterTest");
    }

    @BeforeMethod
    public void bforeMethod(){
        System.out.println("TestNgDemo2.BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("TestNgDemo2.AfterMethod");
    }
}

