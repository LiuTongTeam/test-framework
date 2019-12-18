package io.cex.test.framework.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * @Classname WebBaseCase
 * @Description TODO
 * @Date 2019/12/17  15:02
 * @Created by shenqingyan
 */
public class WebBaseCase {
    public static WebDriver initDriver(String browserName){
        if(browserName.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }else{
            return new ChromeDriver();
        }
    }

}
