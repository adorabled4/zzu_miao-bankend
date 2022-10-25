package com.miao.service.impl;

import ch.qos.logback.core.util.FileUtil;
import cn.hutool.core.lang.UUID;
import com.miao.Exception.BusinessException;
import com.miao.common.BaseResponse;
import com.miao.common.ErrorCode;
import com.miao.constant.SystemConstant;
import com.miao.domain.Animal;
import com.miao.mapper.AnimalMapper;
import com.miao.service.UploadService;
import com.miao.util.CosClientUtil;
import com.miao.util.MyFileUtil;
import com.miao.util.ResultUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Upload;
import jodd.cache.FIFOCache;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

/**
 * @author Dhx_
 * @className UploadServiceImpl
 * @description TODO
 * @date 2022/10/25 17:26
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    AnimalMapper animalMapper;

    /**
     * 上传动物的图片
     * @param images
     * @return 返回图片url 数组地址
     */
    @Override
    public BaseResponse<String> uploadPictures(MultipartFile[] images, Animal animal) {
        String[] fileUrls = new String[images.length];
        int idx = 0;
        StringBuilder urls = null;
        for (MultipartFile image : images) {
            try {
                File file = MyFileUtil.multipartFileToFile(image);
                fileUrls[idx++] = CosClientUtil.uploadFile(file, CosClientUtil.ANIMAL_FILE);
                MyFileUtil.deleteTempFile(file);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传失败");
            }
            urls = new StringBuilder(fileUrls[0]);
            for (int i = 1; i < fileUrls.length; i++) {
                urls.append(",").append(fileUrls[i]);
            }
        }
        animal.setPictures(urls.toString());
        int insert = animalMapper.insert(animal);
        return ResultUtil.success("上传成功！");
    }

}
