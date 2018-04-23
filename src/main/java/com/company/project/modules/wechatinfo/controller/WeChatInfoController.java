package com.company.project.modules.wechatinfo.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.modules.wechatinfo.model.WeChatInfo;
import com.company.project.modules.wechatinfo.service.WeChatInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flynacl
 * @date 2018-03-29
 */
@RestController
@RequestMapping("wechatinfo")
public class WeChatInfoController {
    @Resource
    private WeChatInfoService weChatInfoService;

    @PostMapping
    public Result add(@RequestBody WeChatInfo weChatInfo) {
        weChatInfoService.save(weChatInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        weChatInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody WeChatInfo weChatInfo) {
        weChatInfoService.update(weChatInfo);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        WeChatInfo weChatInfo = weChatInfoService.findById(id);
        return ResultGenerator.genSuccessResult(weChatInfo);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WeChatInfo> list = weChatInfoService.findAll();
        PageInfo<WeChatInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
