package io.cex.test.framework.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static io.cex.test.framework.ui.APPBaseCase.driver;

/**
 * @Classname Locator
 * @Description 元素定位方法
 * @Date 2019/11/13 15:43
 * @Created by shenqingyan
 */
@Getter
@Setter
@Slf4j
public class Locator {
    private String element;
    private int waitSec;
    private String locatorName;

    public enum ByType {
        xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
    }
    private ByType byType;
    public Locator() {
    }

    public Locator(String element) {
        this.element = element;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }
    public Locator(String element, int waitSec) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = ByType.xpath;
    }
    public Locator(String element, int waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
    }
    public Locator(String element, int waitSec, ByType byType,String locatorName) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
        this.locatorName=locatorName;
    }
    public String getElement() {
        return element;
    }
    public int getWaitSec() {
        return waitSec;
    }
    public ByType getBy() {
        return byType;
    }
    public void setBy(ByType byType) {
        this.byType = byType;
    }
    public String getLocalorName()
    {
        return locatorName;
    }

}
