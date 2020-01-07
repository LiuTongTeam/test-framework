package io.cex.test.framework.ui;

import io.cex.test.framework.listener.TestngListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.net.URL;


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
        URL url = WebBaseCase.class.getResource("/chromedriver");
        String driverUrl= url.getPath();
        System.setProperty("webdriver.chrome.driver", driverUrl);
        System.out.println("hahahaatong");
       // System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
        webDriver = initDriver("chrome");
//       System.setProperty("webdriver.gecko.driver","C:\\tools\\geckodriver-v0.26.0-win64\\geckodriver.exe");
//        webDriver = initDriver("firefox");
    }

    @AfterSuite
    public void clearData(){
        webDriver.quit();
    }

}
