package io.cex.test.framework.ui;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import com.google.common.io.Files;

/**
 * @Classname ScreenShot
 * @Description TODO
 * @Date 2019/12/19  14:14
 * @Created by shenqingyan
 */
@Slf4j
public class ScreenShot {
    public WebDriver driver;
    private String screenName;
    public void setscreenName(String screenName)
    {
        this.screenName=screenName;
    }
    public ScreenShot(WebDriver driver)
    {
        this.driver=driver;
    }
    private File takeScreenshot(String screenPath) {
        File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile, new File(screenPath));
            log.error("错误截图："+screenPath);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            return scrFile;
        }
    }

    public  File takeScreenshot() {
        String screenName =this.screenName+ ".jpg";
        File dir = new File("test-output\\snapshot");
        if (!dir.exists())
        {dir.mkdirs();}
        String screenPath = dir.getAbsolutePath() + "\\" + screenName;
        return this.takeScreenshot(screenPath);
    }
}
