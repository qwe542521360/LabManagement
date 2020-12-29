package com.njust.vuelab.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.vuelab.common.lang.Result;
import com.njust.vuelab.entity.Experiment;
import com.njust.vuelab.service.ExperimentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈浩东
 * @since 2020-12-28
 */
@RestController
public class ExperimentController {

    @Autowired
    ExperimentService experimentService;

    @GetMapping("/experiments")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 9);
        IPage pageData = experimentService.page(page,new QueryWrapper<Experiment>());
        return Result.success(pageData);
    }

    @GetMapping("/experiment/{id}")
    public Result detail(@PathVariable(name = "id") Integer id){
        Experiment experiment = experimentService.getById(id);
        Assert.notNull(experiment,"未能查找到该实验");

        return Result.success(experiment);
    }

    @GetMapping("/experiment/edit")
    public Result edit(@RequestBody Experiment experiment){

        Experiment temp = null;
        if (experiment.getId() != null){
            temp = experimentService.getById(experiment.getId());
        }
        else {
            temp = new Experiment();
            temp.setContent(experiment.getContent());
            temp.setDoTime(experiment.getDoTime());
        }

        BeanUtil.copyProperties(experiment,temp,"id");
        experimentService.saveOrUpdate(temp);

        return Result.success(null);
    }

}
