package io.cex.test.framework.ui;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;



/**
 * @Classname WebElementAction
 * @Description TODO
 * @Date 2019/12/17  16:48
 * @Created by shenqingyan
 */
@Slf4j
public class WebElementAction extends WebBaseCase{
    private ElementUtil elementUtil = new ElementUtil(webDriver);

    /**
     * 元素click操作
     * @param locator 一组元素定位信息
     */
    public void click(Locator locator){
        //判断element是否为空 null和非null的处理方式
        try{
            WebElement webElement=elementUtil.findElement(locator);
            webElement.click();
        }catch (NoSuchElementException e){
            log.error("你要点击的元素不存在");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 元素sendKeys操作
     * @param locator 一组元素定位信息
     */
    public void sendKeys(Locator locator,String context){
        //判断element是否为空 null和非null的处理方式
        try{
            WebElement webElement=elementUtil.findElement(locator);
            webElement.sendKeys(context);
        }catch (NoSuchElementException e){
            log.error("你要输入的元素不存在");
            e.printStackTrace();
            throw e;
        }
    }

    /**
    * @desc 刷新操作
    **/
    public void refresh(){
        webDriver.navigate().refresh();
    }

    /**
     * @desc 前进操作
     **/
    public void forward(){
        webDriver.navigate().forward();
    }
    /**
     * @desc 后退操作
     **/
    public void back(){
        webDriver.navigate().back();
    }
    /**
     * @desc 窗口最大化
     **/
    public void windowMaxSize(){
        webDriver.manage().window().maximize();
    }
}
