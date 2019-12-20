package io.cex.test.framework.listener;

import io.cex.test.framework.ui.ScreenShot;
import io.cex.test.framework.ui.WebBaseCase;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.*;

/**
 * @Classname TestngListener
 * @Description TODO
 * @Date 2019/12/19  14:33
 * @Created by shenqingyan
 */
public class TestngListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        byte[] screenshotAs = ((TakesScreenshot)WebBaseCase.webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("失败截图时如下: ","image/png",new ByteArrayInputStream(screenshotAs),".png");
    }

}
