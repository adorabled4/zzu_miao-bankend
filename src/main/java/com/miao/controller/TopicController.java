package com.miao.controller;

import com.miao.common.BaseResponse;
import com.miao.service.TopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @GetMapping("{id}")
    public BaseResponse getTopicInfo(@PathVariable("id")Long topicId){

        return topicService.queryTopicById(topicId);
    }



}
