package com.company.project.modules.user.service.impl;

import com.company.project.core.AbstractService;

import com.company.project.modules.user.dao.UserMapper;
import com.company.project.modules.user.model.User;
import com.company.project.modules.user.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author flynacl
 * @date 2018-03-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
