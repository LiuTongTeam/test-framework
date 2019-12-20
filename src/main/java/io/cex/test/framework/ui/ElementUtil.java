package io.cex.test.framework.ui;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * @Classname ElementUtil
 * @Description TODO
 * @Date 2019/12/17  16:58
 * @Created by shenqingyan
 */
@Slf4j
public class ElementUtil {
    private static WebDriver driver;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }
/*    @Attachment(value = "失败截图如下：",type = "image/png")
    public byte[]  takePhoto(WebDriver driver){
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }*/
    public static ArrayList<Exception> noSuchElementExceptions=new ArrayList<Exception>();

    /**
     * 查找一组元素
     * @param locator 元素定位信息
     * @return
     */
    public List<WebElement> findElements(final Locator locator)
    {
        List<WebElement>  webElements=null;
        try {
            webElements=( new FluentWait<>(driver).withTimeout(20, SECONDS).pollingEvery(5, SECONDS).ignoring(NoSuchElementException.class)).until(
                    new ExpectedCondition<List<WebElement>>() {

                        @Override
                        public List<WebElement> apply(WebDriver driver) {
                            // TODO 自动生成的方法存根
                            List<WebElement> element=null;
                            element=getElements(locator);
                            return element;}
                    });
            return webElements;
        } catch (NoSuchElementException e) {
            Date nowDate=new Date();
            log.info("----------------无法定位页面元素"+formatDate(nowDate));
            e.printStackTrace();
            noSuchElementExceptions.add(e);
//            takePhoto(driver);
            return webElements;
        }
        catch (TimeoutException | ElementNotVisibleException e) {
            Date nowDate=new Date();
            log.info("------------------查找页面元素超时"+formatDate(nowDate));
            e.printStackTrace();
            //截图
//            takePhoto(driver);
            noSuchElementExceptions.add(e);
            return webElements;
        }
    }
    public WebElement findElement( final Locator locator)
    {
        /**
         * 查找某个元素等待几秒
         */
        //Waitformax(Integer.valueOf(locator.getWaitSec()));
        WebElement webElement=null;
        try {
            webElement=( new FluentWait<>(driver).withTimeout(20, SECONDS).pollingEvery(5, SECONDS).ignoring(NoSuchElementException.class))
                    .until(
                    new ExpectedCondition<WebElement>() {

                        @Override
                        public WebElement apply(WebDriver driver) {
                            WebElement element=null;
                            element=getElement(locator);
                            return element;
                        }
                    });
            return webElement;
        } catch (NoSuchElementException e) {
            Date nowDate=new Date();
            log.info("----------------无法定位页面元素"+formatDate(nowDate));
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            //设置截图
//            takePhoto(driver);
            return webElement;
        }
        catch (TimeoutException |ElementNotVisibleException e) {
            Date nowDate=new Date();
            log.info("--------------超时无法定位页面元素"+formatDate(nowDate));
            e.printStackTrace();
            noSuchElementExceptions.add(e);
            //设置截图
//            takePhoto(driver);
            return webElement;
        }
    }

    /**
     * 通过定位信息获取元素
     * @param locator  元素locator
     * @return 返回WebElement
     * @throws NoSuchElementException 找不到元素异常
     */
    public WebElement getElement(Locator locator)
    {

        /**
         * locator.getElement(),获取对象库对象定位信息
         */
        log.info("查找元素："+locator.getLocalorName()+"方式"+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
        WebElement webElement;
        switch (locator.getBy())
        {
            case xpath :
                webElement=driver.findElement(By.xpath(locator.getElement()));
                break;
            case id:
                webElement=driver.findElement(By.id(locator.getElement()));
                break;
            case cssSelector:
                webElement=driver.findElement(By.cssSelector(locator.getElement()));
                break;
            case name:
                webElement=driver.findElement(By.name(locator.getElement()));
                break;
            case className:
                webElement=driver.findElement(By.className(locator.getElement()));
                break;
            case linkText:
                webElement=driver.findElement(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                webElement=driver.findElement(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                webElement=driver.findElement(By.tagName(locator.getElement()));
                break;
            default :
                webElement=driver.findElement(By.xpath(locator.getElement()));
                break;

        }
        return webElement;
    }
    /**
     * 通过定位信息获取一组元素
     * @param locator  元素locator
     * @return 返回WebElement
     * @throws NoSuchElementException 找不到元素异常
     */
    @SuppressWarnings("unchecked")
    public List<WebElement> getElements(Locator locator)
    {
        /**
         * locator.getElement(),获取对象库对象定位信息
         */
        log.info("查找一组元素："+locator.getLocalorName()+" 方式"+"["+"By."+locator.getBy()+":"+locator.getElement()+"]");
        List<WebElement> webElements;
        switch (locator.getBy())
        {
            case xpath :
                webElements=driver.findElements(By.xpath(locator.getElement()));
                /**
                 * 出现找不到元素的时候，记录日志文件
                 */
                break;
            case id:
                webElements=driver.findElements(By.id(locator.getElement()));
                break;
            case cssSelector:
                webElements=driver.findElements(By.cssSelector(locator.getElement()));
                break;
            case name:
                webElements=driver.findElements(By.name(locator.getElement()));
                break;
            case className:
                webElements=driver.findElements(By.className(locator.getElement()));
                break;
            case linkText:
                webElements=driver.findElements(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                webElements=driver.findElements(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                webElements=driver.findElements(By.partialLinkText(locator.getElement()));
                break;
            default :
                webElements=driver.findElements(By.xpath(locator.getElement()));
                break;

        }
        return webElements;
    }
    private static String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date);
    }

}
