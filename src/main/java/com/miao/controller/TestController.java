package com.miao.controller;

import cn.hutool.core.bean.BeanUtil;
import com.miao.DTO.TopicDTO;
import com.miao.Exception.BusinessException;
import com.miao.common.BaseResponse;
import com.miao.common.ErrorCode;
import com.miao.domain.Topic;
import com.miao.domain.User;
import com.miao.mapper.TopicMapper;
import com.miao.mapper.UserMapper;
import com.miao.util.CosClientUtil;
import com.miao.util.MyFileUtil;
import com.miao.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dhx_
 * @className TestController
 * @description TODO
 * @date 2022/10/27 22:05
 */
@RequestMapping("/test")
@Controller
@Slf4j
public class TestController {
    @Resource
    TopicMapper topicMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    CosClientUtil cosClientUtil;

    /**
     * 根据id 查询 文章
     * @param id topicID
     * @param model
     * @return 定位到thymeleaf 解析器
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Topic topic = topicMapper.selectById(id);
        TopicDTO topicDTO = BeanUtil.copyProperties(topic, TopicDTO.class);
        Long userId = 1L;
        User user = userMapper.selectById(userId);
        topicDTO.setAuthor(user.getUserName());
        log.info("帖子标题: {}",topicDTO.getTopicTitle());
        model.addAttribute("topicDTO", topicDTO);
        return "showTopic";
    }



    @GetMapping("/showTiny/{id}")
    public String showTiny(@PathVariable("id") int id, Model model) {
        Topic topic = topicMapper.selectById(id);
        TopicDTO topicDTO = BeanUtil.copyProperties(topic, TopicDTO.class);
        Long userId = 1L;
        User user = userMapper.selectById(userId);
        topicDTO.setAuthor(user.getUserName());
        log.info("帖子标题: {}",topicDTO.getTopicTitle());
        model.addAttribute("topicDTO", topicDTO);
        return "showTiny";
    }
    @GetMapping("/toEdit")
    public String toSimple() {
        log.info("/toEdit");
        return "editorTest";
    }

    @GetMapping("/toTiny")
    public String toTiny() {
        log.info("/toEdit");
        return "TinyMCE";
    }


    @PostMapping("/file")
    @ResponseBody
    //editormd-image-file
    public Object  uploadTopicPicTure(@RequestParam(value = "file") MultipartFile image){
        String url = uploadFile(CosClientUtil.TOPIC_FILE, image);
        System.out.println("picture url :"+url);
        log.info("/uploadTopicPicTure");
        Map<String, String> map = new HashMap<>();
        map.put("location",url);
        return map;
    }




    @PostMapping("/addTopic")
    public String addTopic(TopicDTO topicDTO,Model model) {
        log.info("/addTopic");
        Long userId= 1L;
//        String url = uploadFile(CosClientUtil.TOPIC_FILE);
        Topic topic = BeanUtil.copyProperties(topicDTO, Topic.class);
        if(topicDTO.getTopicContent()==null){
            log.warn("topicDTO  content null");
        }else{
            log.warn("content : \n"+topicDTO.getTopicContent());
        }
        topic.setUserId(userId);
//        topic.setPictureUrls(url);
        int insert = topicMapper.insert(topic);
        model.addAttribute("topicDTO",topicDTO);
        return "showTiny";
    }

    /**
     * 上传图片
     * @param image 需要上传的图片
     * @return 返回json对象
     */
    @ResponseBody
    public BaseResponse<String> uploadPicTure(MultipartFile image) {
        String url = uploadFile(CosClientUtil.TOPIC_FILE, image);
        System.out.println("picture url :"+url);
        return ResultUtil.success(url);
    }

    /**
     * 上传图片
     * @param folder 目录
     * @param images 图片
     * @return 返回上传的图片的 url (多个url使用, 拼接)
     */
    private String uploadFile(String folder,MultipartFile...images){
        if(images==null||images.length==0){
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
}
