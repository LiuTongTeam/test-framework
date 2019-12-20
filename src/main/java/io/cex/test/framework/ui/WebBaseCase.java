package io.cex.test.framework.ui;

import io.cex.test.framework.listener.TestngListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


/**
 * @Classname WebBaseCase
 * @Description TODO
 * @Date 2019/12/17  15:02
 * @Created by shenqingyan
 */
@Listeners({TestngListener.class})
public class WebBaseCase {
    public static WebDriver webDriver;

    public static WebDriver initDriver(String browserName){
        if(browserName.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }else{
            return new ChromeDriver();
        }
    }

    public WebDriver getDriver(){
        return webDriver;
    }
    @BeforeSuite
    public void initData(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        webDriver = initDriver("chrome");
/*        System.setProperty("webdriver.gecko.driver","C:\\tools\\geckodriver-v0.26.0-win64\\geckodriver.exe");
        webDriver = initDriver("firefox");*/
    }

    @AfterSuite
    public void clearData(){
        webDriver.quit();
    }

}
