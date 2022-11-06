package com.miao.controller;

import com.miao.DTO.AnimalDTO;
import com.miao.common.BaseResponse;
import com.miao.service.FollowService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dhx_
 * @className FollowController
 * @date : 2022/11/05/ 23:26
 **/
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    FollowService followService;

    @PutMapping("/animal/{id}/{isFollow}")
    @ApiOperation("关注/取关 动物")
    @ApiResponse(code=200, message = "操作成功")
    public BaseResponse<String> followAnimal(@PathVariable("id") Long followAnimalId, @PathVariable("isFollow") Boolean isFollow) {
        return followService.followAnimal(followAnimalId, isFollow);
    }
    @GetMapping("/commons/{id}")
    @ApiOperation("查看共同关注的动物") // @RequestParam("type")
    public BaseResponse<List<AnimalDTO>> followCommonAnimals(@PathVariable("id") Long userId){
        return followService.followCommonAnimals(userId);
    }

    @PutMapping("/user/{id}/{isFollow}")
    @ApiOperation("关注/取关 用户")
    @ApiResponse(code=200, message = "操作成功")
    public BaseResponse<String> followUser(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow) {
        return followService.followUser(followUserId, isFollow);
    }

    //判断是否关注 可能是动物 / 用户
    @GetMapping("/or/not/{id}")
    @ApiOperation("关注/取关 用户") // @RequestParam("type")
    @ApiResponse(code=200, message = "返回是否已经关注")
    public BaseResponse<Boolean> isFollow(@PathVariable("id") Long objectId,
                                          @ApiParam(name = "关注的对象:动物1/用户2",required = true,defaultValue = "1")Integer type) {
        return followService.isFollow(objectId,type);
    }


}
