package io.cex.test.framework.ui;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * @author shenqingyan
 * @create 2019/11/14
 * @desc UI测试初始化
 **/
@Slf4j
public class APPBaseCase {
    public static AndroidDriver driver;
    //方法描述
    public static String description;
    @BeforeTest
    @Parameters({"driverName","nodeURL","appName","deviceName","sdkVersion","platformName"})
    public void  setup( String driverName,String nodeURL,String appName,String deviceName,String sdkVersion,String platformName) {
        log.info("------------------开始执行测试---------------");
        //启动appium server
        APPElementAction action=new APPElementAction();
        log.info("------------通过cmd命令启动appium server-----------");
        try {
            String cmd="appium -a " + nodeURL.split(":")[0]+ "  -p "+nodeURL.split(":")[1] + "--no-reset";
            System.out.println(cmd);
            action.executeCmd(cmd);
            action.sleep(20);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(nodeURL.equals("")||nodeURL.isEmpty())
        {
            log.error("appium url 没有设置");
        }
        else {
            log.info("读取xml配置：Mobile Driver:"+driverName+"；Appium Server:"+"http://"+nodeURL+"/wd/hub");
            try {
               driver=setRemoteDriver(driverName, nodeURL, appName, deviceName, sdkVersion,platformName);
  //             driver.switchTo().alert().accept();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("appium环境配置失败");
            }
        }
    }

    @AfterTest
    public void tearDown() throws IOException {
        this.driver.quit();
        APPElementAction action=new APPElementAction();
        log.info("关闭appium server");
//        action.executeCmd("taskkill /im cmd.exe");
        log.info("-------------结束测试，并关闭退出driver及appium server-------------");
    }

    /**
     *
     * @param driverName driverName
     * @param nodeURL   appium URL
     * @param appName   app文件名
     * @param deviceName 设备名字
     * @param sdkVersion 安卓版本
     * @param platformName 设备平台    真机：Android AVD虚拟设备：Android Emulator
     * @return
     * @throws MalformedURLException
     */
    private AndroidDriver setRemoteDriver(String driverName,String nodeURL,String appName,String deviceName,String sdkVersion,String platformName) throws MalformedURLException
    {
        //获取app路径
        System.out.println(System.getProperty("user.dir"));
        File classRootPath=new File(System.getProperty("user.dir"));
        File appDir=new File(classRootPath,"apps");
        File app =new File(appDir,appName);
        switch (driverName)
        {
            case "AndroidDriver" :
                //设置自动化相关参数
                DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
                desiredCapabilities.setCapability("platformName",platformName);
                desiredCapabilities.setCapability("deviceName",deviceName );
                desiredCapabilities.setCapability("platformVersion", sdkVersion);
                //设置app路径
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                //设置app主包名和主类名
//                //设置支持中文输入
                desiredCapabilities.setCapability("unicodeKeyboard", "True");
                desiredCapabilities.setCapability("resetKeyboard", "True");
                desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, "True");
                desiredCapabilities.setCapability("noSign", "True");
                //初始化driver
                driver= new AndroidDriver(new URL("http://"+nodeURL+"/wd/hub"), desiredCapabilities);
                break;
            default:
                //获取app路径
                File classRootPath2=new File(System.getProperty("usr.dir"));
                File appDir2=new File(classRootPath2,"apps");
                File app2 =new File(appDir2,appName);
                //设置自动化相关参数
                DesiredCapabilities desiredCapabilities2=new DesiredCapabilities();
                desiredCapabilities2.setCapability("platformName", "Android");
                desiredCapabilities2.setCapability("deviceName",deviceName );
                desiredCapabilities2.setCapability("platformVersion", sdkVersion);
                //设置app路径
                desiredCapabilities2.setCapability("app", app2.getAbsolutePath());
//                //设置支持中文输入
                desiredCapabilities2.setCapability("unicodeKeyboard", "True");
                desiredCapabilities2.setCapability("resetKeyboard", "True");
                desiredCapabilities2.setCapability("noSign", "True");
                //初始化driver
                driver= new AndroidDriver(new URL("http://"+nodeURL+"/wd/hub"), desiredCapabilities2);
                break;
        }
        return driver;
    }


}
