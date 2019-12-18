package io.cex.test.framework.ui;

import lombok.extern.slf4j.Slf4j;
import com.google.common.io.Files;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * @author shenqingyan
 * @create 2019/11/12
 * @desc APP元素操作
 **/
@Slf4j
public class APPElementAction extends APPBaseCase {
    private ElementUtil elementUtil = new ElementUtil(driver);
    private TouchAction touchAction=new TouchAction(driver);

    /**
     * 安装app
     */
    public  void installApp(String appPath)
    {
        driver.installApp(appPath);
    }

    /**
     * 判断是否已安装app
     * @param PackageName
     */
    public  boolean  isInstallApp(String PackageName)
    {
        return  driver.isAppInstalled(PackageName);
    }
    /**
     * 判断是否锁屏
     */
    public  boolean  isLockedscreen()
    {
        return  driver.isLocked();
    }

    /**
     * 打开通知栏界面
     */
    public void openNotifications()
    {
        driver.openNotifications();
    }
    /**
     * 按下安卓手机键.例如home,back等
     * @param androidKeyCode 通过 AndroidKeyCode 枚举类获取
     */
    public  void pressAndroidKey(int androidKeyCode )
    {
        driver.pressKeyCode(androidKeyCode);
    }

    /**
     * 长按下安卓手机键.例如home,back等
     * @param androidKeyCode 通过 AndroidKeyCode 枚举类获取
     */
    public void longPressAndroidKey(int androidKeyCode)
    {
        driver.longPressKeyCode(androidKeyCode);
    }
    /**
     *长按某个元素
     * @param locator  元素locator
     */
    public  void longPressAppElement(Locator locator)
    {
        touchAction.longPress(elementUtil.findElement(locator));

    }

    /**
     * 长按某个元素某个位置
     * @param locator 元素定位信息
     * @param x 元素X坐标
     * @param y 元素Y坐标
     */
    public  void longPressAppElement(Locator locator,int x,int y)
    {
        touchAction.longPress(elementUtil.findElement(locator),x,y);
    }

    /**
     * 长按手机界面某个位置
     * @param x
     * @param y
     */
    public  void longPressPosition(int x,int y)
    {
        touchAction.longPress(x,y);

    }

    /**
     * 按住手机界面某个位置
     * @param x
     * @param y
     */
    public  void pressPosition(int x,int y)
    {
        touchAction.press(x,y);

    }

    /**
     * 按住某个元素
     * @param locator  元素定位信息
     */
    public  void pressAppElement(Locator locator)
    {
        touchAction.press(elementUtil.findElement(locator));
    }

    /**
     * 按住某个元素的某个位置
     * @param locator 元素定位信息
     * @param x 位置x坐标
     * @param y 位置y坐标
     */
    public  void  pressAppElement(Locator locator,int x,int y)
    {
        touchAction.press(elementUtil.findElement(locator),x,y);
    }

    /**
     * 取消操作
     */
    public  void cancle()
    {
        touchAction.cancel();
    }

    /**
     * 移动到某个元素上
     * @param locator
     */
    public void movetoElement(Locator locator)
    {
        touchAction.moveTo(elementUtil.findElement(locator));
    }

    /**
     * 从x,y目标移动到元素
     * @param locator
     * @param x
     * @param y
     */
    public  void movetoElementPostion(Locator locator,int x,int y)
    {
        touchAction.moveTo(elementUtil.findElement(locator),x,y);
    }

    /**
     * 移动到某个位置
     * @param x
     * @param y
     */
    public  void movetoPostion(int x,int y)
    {
        touchAction.moveTo(x,y);
    }

    /**
     * 从一个地方滑动到另外一个地方，等待几秒松开
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param wait 等待几秒松开
     */
    public  void swipe(int x1,int y1,int x2,int y2,int wait)
    {
        driver.swipe(x1,y1,x2,y2,wait);
    }

    /**
     * 在控件中心点轻按下
     * @param locator
     */
    public  void tap(Locator locator)
    {
        touchAction.tap(elementUtil.findElement(locator));
    }
    /**
     * 在控件某个点轻按下
     * @param locator
     */
    public  void tap(Locator locator,int x,int y)
    {
        touchAction.tap(elementUtil.findElement(locator),x,y);
    }
    /**
     * 文本框输入操作
     * @param locator  元素locator
     * @param value 输入值
     */
    public void type(Locator locator,String value)
    {
        try {
            WebElement webElement=elementUtil.findElement(locator);
            webElement.sendKeys(value);
            log.info("input输入："+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"  value:"+"*******"+"]");
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("找不到元素，input输入失败:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            e.printStackTrace();
        }

    }
    public void type_action(Locator locator,String value)
    {
        Actions actions =new Actions(driver);
        WebElement weElement=elementUtil.findElement(locator);
        actions.sendKeys(weElement, value);
    }
    /**
     * 普通单击操作
     * @param locator  元素locator
     */
    public  void click(Locator locator)
    {
        try {
            WebElement webElement=elementUtil.findElement(locator);
            webElement.click();
            log.info("click元素："+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]成功！");
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("找不到元素，click失败:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            e.printStackTrace();
            throw e;
        }

    }
    /**
     * 选择下拉框操作
     * @param locator  元素locator
     * @param text 选择下拉值
     */
    public  void selectByText(Locator locator,String text)
    {
        try {
            WebElement webElement=elementUtil.findElement(locator);
            Select select=new Select(webElement);
            log.info("选择select标签："+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            try {
                select.selectByVisibleText(text);
                log.info("选择下拉列表项：" + text);

            } catch (NoSuchElementException notByValue) {
                // TODO: handle exception
                log.info("找不到下拉值，选择下拉列表项失败 " + text);
                throw notByValue;
            }
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("找不到元素，选择select标签失败:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            throw e;
        }
    }
    /**
     * 选择下拉框操作
     * @param locator  元素locator
     * @param value 选择下拉value
     */
    public  void selectByValue(Locator locator,String value)
    {
        Select select;
        try {
            WebElement webElement=elementUtil.findElement(locator);
            select=new Select(webElement);
            log.info("选择select标签:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("找不到元素，选择select标签失败:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            throw e;
        }
        try {
            select.selectByValue(value);
            log.info("选择下拉列表项：" + value);

        } catch (NoSuchElementException notByValue) {
            // TODO: handle exception
            log.info("找不到下拉值，选择下拉列表项失败 " + value);
            throw notByValue;
        }
    }
    /**
     * 通过下拉列表的index选择元素
     * @param locator
     * @param index
     */
    public void selectByIndex(Locator locator, int index) {
        // TODO 自动生成的方法存根
        Select select;
        try {
            WebElement webElement=elementUtil.findElement(locator);
            select=new Select(webElement);
            log.info("选择select标签:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("找不到元素，选择select标签失败"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
            throw e;
        }
        try {
            select.selectByIndex(index);
            log.info("选择下拉列表项：" + index);

        } catch (NoSuchElementException notByindex) {
            // TODO: handle exception
            log.info("找不到下拉值，选择下拉列表项失败 " + index);
            throw notByindex;
        }
    }
    /**
     * 获取下拉列表的value属性值
     * @param selectLocator 下拉列表 select标签定位信息
     * @return 返回String
     */
    public String  getSelectOptionValue(Locator selectLocator,String optinText)
    {
        WebElement webElement=driver.findElement(By.xpath(selectLocator.getElement()+"//option[text()='"
                + optinText
                + "']"));
        return webElement.getAttribute("value");
    }
    public String getSelectOptionText(Locator selectLocator,String optinValue)
    {
        WebElement webElement=driver.findElement(By.xpath(selectLocator.getElement()+"//option[text()='"
                + optinValue
                + "']"));
        return webElement.getText();
    }
    /**
     * 点击确认按钮
     */
    public void alertConfirm()
    {
        Alert alert=driver.switchTo().alert();
        try {
            alert.accept();
            log.info("点击确认按钮");
        } catch (NoAlertPresentException notFindAlert) {
            // TODO: handle exception
            log.error("找不到确认按钮");
            throw notFindAlert;
        }
    }
    /**
     * 点击取消按钮
     */
    public  void alertDismiss()
    {
        Alert alert= driver.switchTo().alert();
        try {
            alert.dismiss();
            log.info("点击取消按钮");
        } catch (NoAlertPresentException notFindAlert) {
            // TODO: handle exception
            log.error("找不到取消按钮");
            throw notFindAlert;
        }
    }
    /**
     * 获取对话框文本
     * @return 返回String
     */
    public String getAlertText()
    {
        Alert alert=driver.switchTo().alert();
        try {
            String text=alert.getText().toString();
            log.info("获取对话框文本："+text);
            return text;
        } catch (NoAlertPresentException notFindAlert) {
            // TODO: handle exception
            log.error("找不到对话框");
            throw notFindAlert;

        }
    }
    /**
     * 双击操作
     * @param locator  元素locator
     */
    public void click_double(Locator locator)
    {
        WebElement webElement=elementUtil.findElement(locator);
        Actions actions=new Actions(driver);
        actions.doubleClick(webElement).perform();
    }
    /**
     * 清除文本框内容
     * @param locator  元素locator
     */
    public void clear(Locator locator){
        try {
            WebElement webElement=elementUtil.findElement(locator);
            webElement.clear();
            log.info("清除input值:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            log.error("清除input值失败:"+locator.getLocalorName()+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");

        }

    }
    /**
     * 切换frame/iframe框架
     * @param locator  元素locator
     */
    public void switchToFrame(Locator locator)
    {
        WebElement frameElement=elementUtil.findElement(locator);
        driver.switchTo().frame(frameElement);
    }
    /**
     * 切回默认窗口框架
     */
    public void switchToDefaultFrame()
    {
        driver.switchTo().defaultContent();
    }
    /**
     * 多窗口切换
     * @param i 第几个窗口
     */
    public void switchToWindow(int i)
    {
        String[] handls=new String[driver.getWindowHandles().size()];
        driver.getWindowHandles().toArray(handls);
        driver.switchTo().window(handls[i]);
    }
    /**
     * 隐式等待
     * @param  t  最大等待时间，秒为单位
     **/
    public void Waitformax(int t)
    {
        driver.manage().timeouts().implicitlyWait(t, SECONDS);
    }
    /**
     * 获取元素文本
     * @param locator  元素locator
     */
    public String getText(Locator locator)
    {
        WebElement webElement=elementUtil.findElement(locator);
        String text=webElement.getText();
        return text;

    }
    /**
     * 获取元素某属性的值
     * @param locator  元素locator
     * @param attributeName
     * @return 返回String
     */
    public String getAttribute(Locator locator,String attributeName)
    {
        WebElement webElement=elementUtil.findElement(locator);
        String value= webElement.getAttribute(attributeName);
        return value;
    }
    /**
     * 获取当前url
     * @return
     */
    public String getUrl()
    {
        String url=driver.getCurrentUrl();
        return url;
    }
    /**
     * 获取当前网页标题
     * @return 返回String
     */
    public String getTitle()
    {
        String title=driver.getTitle();
        return title;
    }
    /**
     * 截屏方法
     * @param FileUrl  文件保存路径
     * @param Filename  文件名
     * @throws Exception  抛出Exception异常
     */
    public void Snapshot(String FileUrl,String Filename) throws Exception
    {
        File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile, new File(FileUrl+Filename));
            log.info("错误截图："+FileUrl+Filename);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * 显式等待，程序休眠暂停
     * @param time 以秒为单位
     */
    public void sleep(long time)
    {
        try {
            log.info("等待"+time+"秒");
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
/**
     * 显式等待 判断页面是否完全加载完成
    // * @param time 已秒为单位
     */
    public void pagefoload(long time)
    {
        ExpectedCondition<Boolean> pageLoad= new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        FluentWait wait = new FluentWait<>(driver) .withTimeout(30, SECONDS) .pollingEvery(5, SECONDS) .ignoring(NoSuchElementException.class);
//        WebDriverWait wait = new WebDriverWait(driver, time*1000);
        wait.until(pageLoad);
    }
    public void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        System.out.println("执行JS脚本："+js);

    }
    /**
     * 判断一组元素是否存在
     * @param locator 一组元素定位信息
     * @param timeOut 超时时间 秒
     * @return 返回boolean true存在，false不存在
     * @throws InterruptedException
     */
    public  boolean isElementsPresent(Locator locator, int timeOut) throws InterruptedException
    {
        log.info("等待"+timeOut+"秒判断元素："+locator.getElement()+"是否存在");
        boolean isPresent = false;
        Thread.sleep(timeOut * 1000);
        List<WebElement> we =elementUtil.findElements(locator);
        if (we.size() != 0) {
            isPresent = true;
        }
        log.info("判断结果为："+isPresent);
        return isPresent;
    }
    /**
     * 执行cmd命令
     */
    public  void executeCmd(String cmd) throws IOException {
        Runtime runtime=Runtime.getRuntime();
        runtime.exec("cmd /c start "+cmd);
    }
    /**
     * 判断元素是否显示
     * @param locator 元素定位信息
     * @return  返回boolean true显示，false隐藏
     */
    public boolean isElementDisplayed(Locator locator)
    {
        WebElement webElement=elementUtil.findElement(locator);
        webElement.isEnabled();
        log.info("元素显示状态为："+ webElement.isDisplayed());
        return webElement.isDisplayed();
    }
    /**
     * 等待30秒让元素可见
     * @param locator
     */
    public void DisplayElement(Locator locator)
    {
        FluentWait wait = new FluentWait<>(driver) .withTimeout(30, SECONDS) .pollingEvery(5, SECONDS) .ignoring(NoSuchElementException.class);
        //WebDriverWait webDriverWait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(elementUtil.findElement(locator)));

    }

}