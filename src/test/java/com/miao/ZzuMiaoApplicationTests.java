package com.miao;

import cn.hutool.core.lang.UUID;
import com.miao.util.CosClientUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class ZzuMiaoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void cosTest() {
// 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = "AKIDhSrAKOZdZK5nw1BRYYQdllbIvU4Vnwzv";
        String secretKey = "sbfZKvMF5e6O4iEcr6ED4pRzRn1Z5ZxG";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-beijing");
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            System.out.println("bucketName : "+bucketName);
            System.out.println("bucketLocation : "+bucketLocation);
        }
        // 指定要上传的文件
        String localFilePath="C:\\Users\\lenovo\\Pictures\\v2-2eb235a75af128a0374984559350242e_r.jpg";
        File localFile = new File(localFilePath);
        String fileName = localFile.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

// 指定文件将要存放的存储桶
        String bucketName = "typora-1308522872";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "test/"+ UUID.randomUUID().toString()+"."+suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
    }

    @Resource
    CosClientUtil cosClientUtil;

    @Test
    public void cosClientTest() throws IOException {
        String url = cosClientUtil.uploadFile(new File("C:\\Users\\lenovo\\Pictures\\Saved Pictures\\mallard_anas_platyrhynchos_drake_green_metallic_sparkle_bill_yellow-1362404.jpg"),
                CosClientUtil.AVATAR_FILE);
        System.out.println(url);
    }
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void Test() {
        stringRedisTemplate.opsForValue().set("test","test");
        String test = stringRedisTemplate.opsForValue().get("test");
        System.out.println(test);
    }
}
