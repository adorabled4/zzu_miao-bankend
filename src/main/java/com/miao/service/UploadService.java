package com.miao.service;

import com.miao.DTO.AnimalDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.Animal;
import com.miao.domain.Topic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Dhx_
 * @className UploadService
 * @description TODO
 * @date 2022/10/25 17:26
 */
public interface UploadService {
    BaseResponse<String> uploadAnimal(MultipartFile[] images, AnimalDTO animalDTO);

    BaseResponse<String> uploadAvatar(MultipartFile avatar);

    BaseResponse<String> uploadTopic(TopicDTO topicDTO);

    BaseResponse<String> uploadTopicPicTure(MultipartFile image);

    String uploadFile(String folder,MultipartFile...images);

}
