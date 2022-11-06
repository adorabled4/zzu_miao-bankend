package com.miao.controller;

import com.miao.common.BaseResponse;
import com.miao.domain.Comment;
import com.miao.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/topic")
    @ApiOperation("发布评论")
    @ApiResponse(code=200,message = "评论成功")
    public BaseResponse<String> releaseComment(@RequestBody Comment comment){
        return commentService.releaseComment(comment);
    }
    @PutMapping("/like/{id}")
    @ApiOperation("点赞/取消点赞")
    @ApiResponse(code=200,message ="操作成功")
    public BaseResponse<String> likeComment(@PathVariable("id")Long commentId ){
        return commentService.likeComment(commentId);
    }

//    /**
//     * 查看点赞列表
//     * @param id
//     * @return
//     */
//    @GetMapping("/likes/{id}")
//    @ApiOperation("查看帖子的点赞列表")
//    @ApiResponse(code=200,message ="操作成功")
//    public BaseResponse<String> queryBlogLikes(@PathVariable("id")Long id){
//        return commentService.queryCommentLikes(id);
//    }


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
