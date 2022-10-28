package com.miao.controller;

import com.miao.DTO.AnimalDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.Animal;
import com.miao.domain.Topic;
import com.miao.service.UploadService;
import com.miao.util.CosClientUtil;
import org.springframework.web.bind.annotation.*;
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
    public BaseResponse<String> uploadAnimal(@RequestParam("file")MultipartFile[] images, AnimalDTO animalDTO) {
        return uploadService.uploadAnimal(images,animalDTO);
    }

    /**
     * 上传用户头像
     * @param avatar
     * @return
     */
    @PostMapping("/user")
    public BaseResponse<String> uploadAvatar(@RequestParam("file")MultipartFile avatar) {
        return uploadService.uploadAvatar(avatar);
    }

    /**
     * 上传帖子
     * @param topicDTO
     * @return
     */
    @PostMapping("/topic")
    public BaseResponse<String> uploadTopic(TopicDTO topicDTO) {
        return uploadService.uploadTopic(topicDTO);
    }

    /**
     * 前端自动上传图片
     * @param image 上传图片
     * @return 返回json对象
     */
    @PostMapping("/file")
    public Object uploadTopicPicTure(@RequestParam(value = "file")MultipartFile image){
        String url = uploadService.uploadFile(CosClientUtil.TOPIC_FILE, image);
        Map<String, String> map = new HashMap<>();
        map.put("location",url);
        return map;
    }
}
