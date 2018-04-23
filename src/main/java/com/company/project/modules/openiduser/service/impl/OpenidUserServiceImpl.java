package com.company.project.modules.openiduser.service.impl;

import com.company.project.core.AbstractService;

import com.company.project.modules.openiduser.dao.OpenidUserMapper;
import com.company.project.modules.openiduser.model.OpenidUser;
import com.company.project.modules.openiduser.service.OpenidUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author flynacl
 * @date 2018-03-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpenidUserServiceImpl extends AbstractService<OpenidUser> implements OpenidUserService {
    @Resource
    private OpenidUserMapper openidUserMapper;

}
