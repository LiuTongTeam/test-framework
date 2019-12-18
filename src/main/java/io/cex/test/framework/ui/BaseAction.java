package io.cex.test.framework.ui;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @Classname BaseAction
 * @Description TODO
 * @Date 2019/11/18  15:18
 * @Created by shenqingyan
 */
@Slf4j
public class BaseAction extends APPBaseCase {

    protected HashMap<String,Locator> locatorMap;
    public String path=null;
    public InputStream path_inputStream_1;
    /**
     * 从对象库获取定位信息
     * @param locatorName 对象库名字
     * @return
     */
    public  Locator getLocator(String locatorName)
    {
        Locator locator;
        /**
         * 在对象库通过对象名字查找定位信息
         */
        locator=locatorMap.get(locatorName);
        /**
         * 加入对象库，找不到该定位信息，就创建一个定位信息
         */
        if(locator==null)
        {
            log.error("没有找到"+locatorName+"页面元素");
        }
        return locator;

    }



    public void getLocatorMap()
    {
        XmlReadUtil xmlReadUtil=new XmlReadUtil();
        try {
            if((path==null||path.isEmpty()))
            {locatorMap = xmlReadUtil.readXMLDocument(path_inputStream_1, this.getClass().getCanonicalName());}
            else {
                locatorMap = xmlReadUtil.readXMLDocument(path, this.getClass().getCanonicalName());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void setXmlObjectPath(String path)
    {
        this.path=path;
    }
}
