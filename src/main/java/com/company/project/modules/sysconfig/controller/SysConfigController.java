package com.company.project.modules.sysconfig.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.modules.sysconfig.model.SysConfig;
import com.company.project.modules.sysconfig.service.SysConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flynacl
 * @date 2018-03-23
 */
@RestController
@RequestMapping("sysconfig")
public class SysConfigController {
    @Resource
    private SysConfigService sysConfigService;

    @PostMapping
    public Result add(@RequestBody SysConfig sysConfig) {
        sysConfigService.save(sysConfig);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysConfigService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody SysConfig sysConfig) {
        sysConfigService.update(sysConfig);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysConfig sysConfig = sysConfigService.findById(id);
        return ResultGenerator.genSuccessResult(sysConfig);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysConfig> list = sysConfigService.findAll();
        PageInfo<SysConfig> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
