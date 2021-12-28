package com.test.day02;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/1/26 17:05
 */
public class ArrayTest {
    public static void main(String []args){
        // 一维数组1,new一个一维数组
        int [] arr1 = new int[3];
        System.out.println(arr1[0]);
        // 一维数组1，初始化一个一维数组
        int [] arr2 = {1,2,3};
        System.out.println(arr2[0]);
        for (int i=0;i<3;i+=1){
         System.out.println(arr2[i]);
        }
    }
}
