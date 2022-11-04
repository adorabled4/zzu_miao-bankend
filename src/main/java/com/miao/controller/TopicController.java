package com.miao.controller;

import com.miao.DTO.CommentDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import com.miao.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Dhx_
 * @className TopicController
 * @description TODO
 * @date 2022/10/31 9:15
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource
    TopicService topicService;

    @GetMapping("/{id}")
    public BaseResponse<TopicDTO> queryTopicInfoById(@PathVariable("id")Long topicId){
        return topicService.queryTopicById(topicId);
    }


    @PutMapping("/{id}")
    public BaseResponse<List<CommentDTO>> queryTopicCommentsById(@PathVariable("id")Long topicId){
        return topicService.queryTopicCommentsById(topicId);
    }


    @PostMapping("/list")
    public BaseResponse<List<TopicDTO>> queryTopicList(){
        return topicService.queryTopicList();
    }


}
