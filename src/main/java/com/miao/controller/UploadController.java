package com.miao.controller;

import com.miao.common.BaseResponse;
import com.miao.domain.Animal;
import com.miao.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Dhx_
 * @className UploadController
 * @description TODO
 * @date 2022/10/25 17:19
 */
@RestController("/upload")
public class UploadController {

    @Resource
    UploadService uploadService;

    /**
     * 动物图片文件上传
     * @param images  传过来的图片
     * @return 直接返回拼接好的url
     */
    @PostMapping("/animal")
    public BaseResponse<String> uploadAnimalPicture(@RequestParam("file")MultipartFile[] images, Animal animal){
        return uploadService.uploadPictures(images,animal);
    }
}
