package com.test.AppAutoTest;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/5/2 19:05
 */
public class AppAutoTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //打开测试的app，初始化所需能力
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        //deviceName
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");

        //platformName
        desiredCapabilities.setCapability("platformName","Android");

        //appPackge
        desiredCapabilities.setCapability("appPackge","com.lemon.lemonban");

        //appActivity
        desiredCapabilities.setCapability("appActivity",
                "com.lemon.lemonban.activity.WelcomeActivity");

        desiredCapabilities.setCapability("automationName",
                "UiAutomator2");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("app", "C:\\Users\\SEMON\\Desktop\\lemonban_release_v2.1.2_finally.apk");

        //与appium服务建立连接,将能力传给appium
        //传入appium通讯地址
        AndroidDriver androidDriver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);

        //找到页面元素-点击我的柠檬
        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(5000);
        androidDriver.findElementById(
                "com.lemon.lemonban:id/navigation_my").click();

        Thread.sleep(1000);
        androidDriver.findElementById("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout").click();

        Thread.sleep(1000);
        //找到页面元素-输入账号
        androidDriver.findElementById("com.lemon.lemonban:id/et_mobile")
                .sendKeys("18770636562");

        //找到页面元素-输入密码
        androidDriver.findElementById("com.lemon.lemonban:id/et_password")
                .sendKeys("636562");

        //显示等待
        //1、实例化WebDriverWait对象
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver,5);
        //2、设置条件until
        By by = By.xpath("");
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        //找到页面元素-点击登录
        androidDriver.findElementById("com.lemon.lemonban:id/btn_login").click();


        //滑动操作
        TouchAction touchAction = new TouchAction(androidDriver);

        //按下起始点 --》滑动到终点 --》松开手指

        //起始点
        PointOption pointOption1  = new PointOption();
        pointOption1.withCoordinates(461,612);

        //终止点
        PointOption pointOption2  = new PointOption();
        pointOption2.withCoordinates(461,133);

        //滑动等待
        WaitOptions waitOptions = new WaitOptions();
        Duration duration = Duration.ofMillis(2000);
        waitOptions.withDuration(duration);
        //按压移动松开释放
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }
}
