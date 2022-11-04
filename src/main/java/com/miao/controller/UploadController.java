package com.miao.controller;

import com.miao.DTO.AnimalDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import com.miao.service.UploadService;
import com.miao.util.CosClientUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dhx_
 * @className UploadController
 * @description TODO
 * @date 2022/10/25 17:19
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    UploadService uploadService;

    /**
     * 动物信息 图片文件上传
     * @param images  传过来的图片
     * @return 直接返回拼接好的url
     */
    @PostMapping("/animal")
    @ApiResponses({@ApiResponse(code=200,message = "动物发布成功",response = java.lang.String.class)})
    public BaseResponse<String> uploadAnimal(@RequestParam("file")MultipartFile[] images, AnimalDTO animalDTO) {
        return uploadService.uploadAnimal(images,animalDTO);
    }

    /**
     * 上传用户头像
     * @param avatar 头像文件
     * @return 返回上传成功
     */
    @PostMapping("/user")
    @ApiResponses({@ApiResponse(code=200,message = "头像上传成功",response = java.lang.String.class)})
    public BaseResponse<String> uploadAvatar(@RequestParam("file")MultipartFile avatar,HttpServletRequest request) {
        return uploadService.uploadAvatar(avatar,request);
    }

    /**
     * 上传帖子
     * @param topicDTO 帖子对象
     * @return 返回上传成功
     */
    @PostMapping("/topic")
    @ApiResponses({@ApiResponse(code=200,message = "上传成功",response = java.lang.String.class)})
    public BaseResponse<String> uploadTopic(TopicDTO topicDTO) {
        return uploadService.uploadTopic(topicDTO);
    }

    /**
     * 前端自动上传图片
     * @param image 上传图片
     * @return 返回json对象
     */
    @PostMapping("/file")
    @ApiOperation("用于富文本编辑器自动上传图片")
    @ApiResponses({@ApiResponse(code=200,message = "图片url",response = java.lang.String.class)})
    public Object uploadTopicPicTure(@RequestParam(value = "file")MultipartFile image){
        String url = uploadService.uploadFile(CosClientUtil.TOPIC_FILE, image);
        System.out.println("picture url :"+url);
        log.info("/uploadTopicPicTure");
        Map<String, String> map = new HashMap<>();
        map.put("location",url);
        return map;
    }

}
