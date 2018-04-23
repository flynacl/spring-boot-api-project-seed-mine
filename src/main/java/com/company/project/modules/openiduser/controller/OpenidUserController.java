package com.company.project.modules.openiduser.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.modules.openiduser.model.OpenidUser;
import com.company.project.modules.openiduser.service.OpenidUserService;
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
@RequestMapping("openiduser")
public class OpenidUserController {
    @Resource
    private OpenidUserService openidUserService;

    @PostMapping
    public Result add(@RequestBody OpenidUser openidUser) {
        openidUserService.save(openidUser);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        openidUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody OpenidUser openidUser) {
        openidUserService.update(openidUser);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        OpenidUser openidUser = openidUserService.findById(id);
        return ResultGenerator.genSuccessResult(openidUser);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OpenidUser> list = openidUserService.findAll();
        PageInfo<OpenidUser> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
