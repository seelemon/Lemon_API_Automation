package com.test.day05;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/4/22 23:02
 */
public class AbMainTest extends AbStractTest{


    public static void main(String[] args) {
        AbStractTest.call();
    }

    @Override
    public void play() {
        System.out.println("big jb do");
    }
}
