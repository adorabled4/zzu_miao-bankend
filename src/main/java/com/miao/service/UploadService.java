package com.miao.service;

import com.miao.common.BaseResponse;
import com.miao.domain.Animal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Dhx_
 * @className UploadService
 * @description TODO
 * @date 2022/10/25 17:26
 */
public interface UploadService {
    BaseResponse<String> uploadPictures(MultipartFile[] images, Animal animal);
}
