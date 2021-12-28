package com.test.day09;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/2 15:17
 */
public class TestNgDemo1 {
    @Test(dependsOnMethods = "test2",alwaysRun = true)
    public void test1(){
        System.out.println("TestNgDemo1.test1");//依赖方法
    }

    @Test
    public void test2(){
        System.out.println("TestNgDemo1.test2");
    }

    @Test(priority = 0)
    public void test3(){
        System.out.println("TestNgDemo1.test3");//优先级
    }

    @Test(invocationCount = 0,threadPoolSize = 0)
    public void test4(){
        System.out.println(Thread.currentThread().getName() + "TestNgDemo1.test4");//多线程,10个线程执行5个并发
         }
}
