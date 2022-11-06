package com.miao.controller;

import com.miao.common.BaseResponse;
import com.miao.domain.Comment;
import com.miao.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dhx_
 * @className CommentController
 * @description TODO
 * @date 2022/10/29 20:53
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @PostMapping("/topic/{id}")
    @ApiOperation("发布评论")
    @ApiResponse(code=200,message = "评论成功")
    public BaseResponse<String> releaseComment(@PathVariable("id")Long topicId , Comment comment){
        return commentService.releaseComment(topicId,comment);
    }
//
//    @PostMapping("/comment/{id}")
//    public BaseResponse replyComment(@PathVariable("id")Long repliedCommentId ,Comment comment){
//        return commentService.replyComment(repliedCommentId,comment);
//    }

//    @GetMapping("/{id}")
//    @ApiResponse(code=200,message = "评论成功")
//    public BaseResponse queryCommentsByTopicId(@PathVariable("id")Long topicId){
//        return  commentService.queryCommentsByTopicId(topicId);
//    }

}
