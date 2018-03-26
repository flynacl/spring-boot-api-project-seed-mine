package com.company.project.modules.role.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.modules.role.dao.RoleMapper;
import com.company.project.modules.role.model.Role;
import com.company.project.modules.role.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author flynacl
 * @date 2018-03-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

}
