package com.miao.service;

import com.miao.DTO.AnimalDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dhx_
 * @className UploadService
 * @description TODO
 * @date 2022/10/25 17:26
 */
public interface UploadService {
    BaseResponse<String> uploadAnimal(MultipartFile[] images, AnimalDTO animalDTO);

    BaseResponse<String> uploadAvatar(MultipartFile avatar,HttpServletRequest request);

    BaseResponse<String> uploadTopic(TopicDTO topicDTO);

    String uploadFile(String folder,MultipartFile...images);

}
