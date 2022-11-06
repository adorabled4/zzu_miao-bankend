package com.miao;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miao.domain.Comment;
import com.miao.domain.User;
import com.miao.sensitivewdfilter.WordFilter;
import com.miao.service.CommentService;
import com.miao.service.UserService;
import com.miao.util.CosClientUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class ZzuMiaoApplicationTests {

    @Resource
    CommentService commentService;

    @Resource
    UserService userService;
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

    /**
     * mybatis-plus 会自动赋值id
     */
    @Test
    public void saveTest() {
        Comment comment=new Comment();
        comment.setUserId(1L);
        comment.setLikeCount(0);
        comment.setRepliedId(15L);
        comment.setCommentContent("这是一个评论!!!!!!!");
        commentService.save(comment);
        System.out.println(comment.getCommentId());
    }

    @Test
    public void getest(){
        userService.register("ado354able34","123456qwer","123456qwer");
    }

    @Test
    public void UserTest(){
        User userServiceById = userService.getById(3);
        System.out.println(userServiceById);
    }

    @Test
    public void TestFilter() {
        String s = "你是逗比吗？ｆｕｃｋ！fUcK,你竟然用法轮功，法@!轮!%%%功";
        System.out.println("解析问题： " + s);
        System.out.println("解析字数 : " + s.length());
        String re;
        long nano = System.nanoTime();
        re = WordFilter.doFilter(s);
        nano = (System.nanoTime() - nano);
        System.out.println("解析时间 : " + nano + "ns");
        System.out.println("解析时间 : " + nano / 1000000 + "ms");
        System.out.println(re);
        System.out.println();

        nano = System.nanoTime();
        System.out.println("是否包含敏感词： " + WordFilter.isContains(s));
        nano = (System.nanoTime() - nano);
        System.out.println("解析时间 : " + nano + "ns");
        System.out.println("解析时间 : " + nano / 1000000 + "ms");
    }

    @Test
    public void test(){
        System.out.println((int)('ｆ'));
    }

    @Test
    public void testPage(){
        //    @Override
//    public Result queryHotBlog(Integer current) {
//        // 根据用户查询
//        Page<Blog> page = this.query()
//                .orderByDesc("liked")
//                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
//        // 获取当前页数据
//        List<Blog> records = page.getRecords();
//        // 查询用户
//        records.forEach(blog -> {
//            queryBlogUser(blog);
//            isBlogLiked(blog);
//        });
//        return Result.ok(page);
//    }
        String userName ="d";
        Page<User> page= userService.query().like("user_name", userName).page(new Page<>(1, 3));
        List<User> records = page.getRecords(); // 所有的数据
        System.out.println(page.getCurrent());   // 当前页码
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (User record : records) {
            record.setUserName("test_userName");
        }
        System.out.println(page.getCurrent());   // 当前页码
    }

    @Test
    public void testPageQuery(){
        Page<User> page = userService.query()
                .like("user_name","i")
                .orderByDesc("create_time")
                .page(new Page<>(3,8));
        page.getRecords().forEach(System.out::println);
    }
    @Test
    public void testInputRedis(){
        for (int i = 1; i <=100; i++) {
            stringRedisTemplate.opsForSet().add("topic:comment:1", String.valueOf(i));
        }
    }


    @Test
    public void testSetUserId(){
        for (Comment comment : commentService.query().list()) {
            comment.setUserId(51L);
        }

    }
}
