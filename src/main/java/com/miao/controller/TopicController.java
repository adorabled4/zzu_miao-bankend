package com.miao.controller;

import com.miao.DTO.CommentDTO;
import com.miao.DTO.TopicDTO;
import com.miao.common.BaseResponse;
import com.miao.service.TopicService;
import io.swagger.annotations.*;
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
@Api(value = "仅供测试",hidden = true)
public class TopicController {

    @Resource
    TopicService topicService;

    @GetMapping("/{id}")
    @ApiOperation("查询帖子信息")
    @ApiResponses({@ApiResponse(code=200,message = "帖子详细信息")})
    public BaseResponse<TopicDTO> queryTopicInfoById(@PathVariable("id")Long topicId){
        return topicService.queryTopicById(topicId);
    }


    @PutMapping("/{id}")
    @ApiOperation("查询评论列表")
    @ApiResponses({@ApiResponse(code=200,message = "评论List" )})
    public BaseResponse<List<CommentDTO>> queryTopicCommentsById(@PathVariable("id")Long topicId,
                        @RequestParam( name= "current" ,defaultValue = "1") @ApiParam(defaultValue = "1",required = true) Integer current){
        return topicService.queryTopicCommentsById(topicId,current);
    }


    @PostMapping("/list")
    @ApiOperation("查询帖子列表")
    @ApiResponses({@ApiResponse(code=200,message = "帖子List" )})
    public BaseResponse<List<TopicDTO>> queryTopicList(
            @RequestParam( name= "current" ,defaultValue = "1") @ApiParam(defaultValue = "1",required = true) Integer current){
        return topicService.queryTopicList(current);
    }
    @PutMapping("/like/{id}")
    @ApiOperation("点赞/取消点赞")
    @ApiResponse(code=200,message ="操作成功")
    public BaseResponse<String> likeComment(@PathVariable("id")Long topicId){
        return topicService.likeTopic(topicId);
    }


}
