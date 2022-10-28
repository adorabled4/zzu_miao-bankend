package com.miao.util;

import cn.hutool.core.lang.UUID;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
* @author Dhx_
* @className CosClientUtil 创建cosClient实例
* @description TODO
* @date 2022/10/25 17:36
*/

@Component
public class CosClientUtil {
    @Value("${spring.redis.host}")
    private String testValue;
    @Value("${tencent.secretId:#{null}}")
    private String secretId;
    @Value("${tencent.secretKey:#{null}}")
    private String secretKey;
    @Value("${tencent.region:#{null}}")
    private String regionName;
    @Value("${tencent.bucket:#{null}}")
    private String bucketName;
    @Value("${tencent.baseUrl:#{null}}")
    private String baseUrl;

    /**
     * 动物图片存储路径
     */
    public static final String ANIMAL_FILE="animal/";
    public static final String AVATAR_FILE="avatar/";
    public static final String TOPIC_FILE="topic/";

    public void testValue(){
        System.out.println(testValue);
    }
    /**
     * 获取实例
     * @return cosClient 实例
     */
    public COSClient getInstance(){
// 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        System.out.println(secretId);
        System.out.println(secretKey);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    /**
     * 上传文件
     * @param file 需要上传的文件
     * @return 返回文件的url
     */
    public String uploadFile(File file,String fileType){
        //获取文件后缀
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        COSClient cosClient = getInstance();
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = fileType+ UUID.randomUUID().toString()+"."+suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        cosClient.putObject(putObjectRequest);
        String url= baseUrl+ key;
        return url;
    }

}
