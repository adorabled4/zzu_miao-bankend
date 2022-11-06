package com.miao.controller;

import com.miao.DTO.AnimalDTO;
import com.miao.common.BaseResponse;
import com.miao.service.AnimalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Dhx_
 * @className AnimalController
 * @description TODO
 * @date 2022/10/25 23:29
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Resource
    AnimalService animalService;
//获取动物列表

    @GetMapping("/list")
    @ApiOperation("查询动物基本信息列表")
    public BaseResponse<List<AnimalDTO>> queryAnimals(){
        return animalService.queryAnimals();
    }
//获取动物详细信息

    @PostMapping("/{id}")
    @ApiOperation("根据id查询动物详细信息")
    public BaseResponse<AnimalDTO> queryAnimalById(@PathVariable("id")Long id){
        return animalService.queryAnimalById(id);
    }

//根据动物物种 /  名称 搜索动物

    @GetMapping("/name")
    @ApiOperation("根据名称查询动物列表")
    public BaseResponse<List<AnimalDTO>>  searchAnimalByName(String animalName){
        return animalService.searchAnimalByName(animalName);
    }

    @GetMapping("/species")
    @ApiOperation("根据物种查询动物列表")
    public BaseResponse<List<AnimalDTO> > searchAnimalBySpecies(String animalName){
        return animalService.searchAnimalBySpecies(animalName);
    }
}
