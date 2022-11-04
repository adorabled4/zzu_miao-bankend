package com.miao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.miao.DTO.AnimalDTO;
import com.miao.DTO.TopicDTO;
import com.miao.DTO.UserDTO;
import com.miao.Exception.BusinessException;
import com.miao.common.BaseResponse;
import com.miao.common.ErrorCode;
import com.miao.domain.Animal;
import com.miao.domain.Topic;
import com.miao.domain.User;
import com.miao.mapper.AnimalMapper;
import com.miao.mapper.TopicMapper;
import com.miao.mapper.UserMapper;
import com.miao.service.UploadService;
import com.miao.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.miao.constant.RedisConstant.LOGIN_TOKEN_KEY;
import static com.miao.constant.RedisConstant.TOPIC_TAG_KEY;

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

    @Resource
    CosClientUtil cosClientUtil;

    @Resource
    UploadService uploadService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserMapper userMapper;

    @Resource
    TopicMapper topicMapper;

    /**
     * 发布动物信息
     * @param images
     * @return 返回图片url 数组地址
     */
    @Override
    public BaseResponse<String> uploadAnimal(MultipartFile[] images, AnimalDTO animalDTO) {
        Long userId= UserHolder.getUser().getUserId();
        String url = uploadFile(CosClientUtil.ANIMAL_FILE,images);
        Animal animal = BeanUtil.copyProperties(animalDTO, Animal.class);
        animal.setCreateUserId(userId);
        animal.setPictures(url);
        int insert = animalMapper.insert(animal);
        return ResultUtil.success("上传成功！");
    }

    /**
     * 上传用户头像
     * @param image 图片
     * @return 返回success
     */
    @Override
    public BaseResponse<String> uploadAvatar(MultipartFile image,HttpServletRequest request) {
        if(image==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"没有上传图片!");
        }
        String avatarUrl="";
        try {
//    1. 转换文件类型
            File file = MyFileUtil.multipartFileToFile(image);
            MyFileUtil.checkFile(file);
//    2. 通过cos上传文件
            avatarUrl = cosClientUtil.uploadFile(file, CosClientUtil.AVATAR_FILE);
            MyFileUtil.deleteTempFile(file);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传失败");
        }
//    3. 通过ThreadLocal获取当前用户
        UserDTO userDTO= UserHolder.getUser();
//    4. 通过id查询用户完整信息
        User user = userMapper.selectById(userDTO.getUserId());
//    5. 更新用户的avatarUrl
        if(StringUtils.isBlank(avatarUrl)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传失败");
        }
        user.setAvatarUrl(avatarUrl);
//    6. 更新redis中保存的userDTO
        userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        //1. 获取请求头中的token
        String token = request.getHeader("authorization");
        //2. 拼接key
        String key= LOGIN_TOKEN_KEY + token;
        //3. bean to map
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                new CopyOptions().ignoreNullValue()
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        //4. 保存新的userDTO
        UserHolder.saveUser(userDTO);
        stringRedisTemplate.opsForHash().putAll(key,userMap);
//    7. 保存更新的数据到数据库
        userMapper.updateById(user);
//    8. 返回
        return ResultUtil.success("上传成功！");
    }


    /**
     * 上传帖子, 帖子的图片已经内置在里面了
     * @param topicDTO
     * @return 上传成功
     */
    @Override
    public BaseResponse<String> uploadTopic(TopicDTO topicDTO) {
        // 敏感词过滤
        String filteredContent = SensitiveWordFilterUtil.filter(topicDTO.getTopicContent());
        String filteredTitle = SensitiveWordFilterUtil.filter(topicDTO.getTopicTitle());
        topicDTO.setTopicContent(filteredContent);
        topicDTO.setTopicTitle(filteredTitle);
        Long userId= UserHolder.getUser().getUserId();
        Topic topic = BeanUtil.copyProperties(topicDTO, Topic.class);
        topic.setUserId(userId);
        int insert = topicMapper.insert(topic);

        String tags= topicDTO.getTags();
        if(StringUtils.isNotBlank(tags)){
            stringRedisTemplate.opsForValue().set(TOPIC_TAG_KEY + topic.getTopicId(), tags);
        }
        return ResultUtil.success("上传成功！");
    }


    /**
     * 上传图片
     * @param folder 目录
     * @param images 图片
     * @return 返回上传的图片的 url (多个url使用, 拼接)
     */
    @Override
    public String uploadFile(String folder, MultipartFile... images){
        if(images==null){
            return "";
        }
        String[] fileUrls = new String[images.length];
        int idx=0;
        StringBuilder urls=null;
        for (MultipartFile image : images) {
            try {
                File file = MyFileUtil.multipartFileToFile(image);
                MyFileUtil.checkFile(file);
                fileUrls[idx++] = cosClientUtil.uploadFile(file, folder);
                MyFileUtil.deleteTempFile(file);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传失败");
            }
            urls = new StringBuilder(fileUrls[0]);
            for (int i = 1; i < fileUrls.length; i++) {
                urls.append(",").append(fileUrls[i]);
            }
        }
        return urls.toString();
    }

    /**
     * 
     * @param animalDTO
     * @param pictureUrls
     * @return 返回animal
     */
    private Animal createAnimal(AnimalDTO animalDTO, String pictureUrls){
        Animal animal = new Animal();
        animal.setAnimalName(animalDTO.getAnimalName());
        animal.setAddress(animalDTO.getAddress());
        animal.setGender(animalDTO.getGender());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setPictures(pictureUrls);
        return animal;
    }

}
