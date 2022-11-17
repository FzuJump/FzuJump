package com.Ambition.controller;

import com.Ambition.Utils.Constant;
import com.Ambition.dto.ResultData;
import com.Ambition.service.ScoreService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "成绩管理")
@RestController
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @ApiOperation("修改成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "goodsName", value = "商品名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "name", value = "类型名", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "goodsDesc", value = "商品描述", required = true, dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "shelfLife", value = "保质期", required = true, dataType = "Integer", dataTypeClass = Integer.class),
    })
    @GetMapping("/score/update")
    public ResultData updateGoods(int id, String userName, String rolename, int jumpFrequency, int itemNumber) {
        return scoreService.updateScore(id, userName, rolename, jumpFrequency, itemNumber);
    }

    @ApiOperation("搜索用户成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsName", value = "商品名", required = true, dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/score/search")
    public ResultData scoreSearch(String userName){
        System.out.println(userName);
        return scoreService.searchScore("%"+userName+"%");
    }

    @ApiOperation("删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @GetMapping("/score/delete")
    public ResultData deleteScore(@ApiParam("商品id")@RequestParam int id,@RequestParam(defaultValue = "1") int pageNo) {
        PageHelper.startPage(pageNo, Constant.LIMIT);
        return scoreService.deleteScore(id);
    }

    @ApiOperation("添加商品")
    @GetMapping("/score/add")
    public ResultData addScore(String userName, String rolename, int jumpFrequency, int itemNumber) {
        return scoreService.addScore(userName, rolename, jumpFrequency, itemNumber);
    }

    @ApiOperation("成绩排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PostMapping("/score")
    public ResultData stock(@RequestParam(required = false, defaultValue = "1") int page){
        PageHelper.startPage(page, Constant.LIMIT);
        return scoreService.GetAllGrades();
    }
}
