package com.company.project.modules.sysconfig.service.impl;

import com.company.project.core.AbstractService;

import com.company.project.modules.sysconfig.dao.SysConfigMapper;
import com.company.project.modules.sysconfig.model.SysConfig;
import com.company.project.modules.sysconfig.service.SysConfigService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author flynacl
 * @date 2018-03-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysConfigServiceImpl extends AbstractService<SysConfig> implements SysConfigService {
    @Resource
    private SysConfigMapper sysConfigMapper;

}
