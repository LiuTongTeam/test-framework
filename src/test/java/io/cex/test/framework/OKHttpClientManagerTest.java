package io.cex.test.framework;

import io.cex.test.framework.common.FileUtil;
import io.cex.test.framework.httputil.OkHttpClientManager;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class OKHttpClientManagerTest {
    @Test
    public void testHeader() throws IOException {
        HashMap header = new HashMap<String, String>();
        header.put("DEVICEID", "A5A6F0c6B90638A2F-e195d43830A5e9979906e5A0A8A-9330A0B3ADBBB9d93-AFF5dBcF9-A4c749-AB10-4EB49EABF9E7-85315174-34961239");
        header.put("DEVICESOURCE","native");
        header.put("Lang","en-US");
        Response response = OkHttpClientManager.post("http://139.9.55.125/apis/user/login", "{\"data\":{\"loginPwd\":\"afdd0b4ad2ec172c586e2150770fbf9e\",\"identifier\":\"18780050295\",\"mobileArea\":\"86\",\"verifyCode\":\"123456\"},\"lang\":\"en-US\"}",
                "application/json", header);
        System.out.println("body:"+response.body().string()+"header"+response.headers().toString());
    }

    @Test
    public void testFileUpLoad() throws IOException{
        HashMap header = new HashMap<String, String>();
        header.put("DEVICEID", "A5A6F0c6B90638A2F-e195d43830A5e9979906e5A0A8A-9330A0B3ADBBB9d93-AFF5dBcF9-A4c749-AB10-4EB49EABF9E7-85315174-34961239");
        header.put("DEVICESOURCE","native");
        header.put("Lang","en-US");
        header.put("CEXTOKEN","njn0dwkw1rvuv4bth73vizfef8t86gafq2fb4zffw557wcxd2zc6z9imyutnd1cs_CEX");
        File file = FileUtil.getFileFromWeb("http://172.29.16.161/1.jpeg");
        Response response = OkHttpClientManager.post("http://139.9.55.125/apis/user/file/upload/file", "{\"data\":{\"loginPwd\":\"afdd0b4ad2ec172c586e2150770fbf9e\",\"identifier\":\"18780050295\",\"mobileArea\":\"86\",\"verifyCode\":\"123456\"},\"lang\":\"en-US\"}",
                "multipart/form-data; boundary=----WebKitFormBoundarylsMUpMX3lOxQKla8", header,file,"1.jpeg");
        System.out.println("body:"+response.body().string()+"header"+response.headers().toString());
    }
}
