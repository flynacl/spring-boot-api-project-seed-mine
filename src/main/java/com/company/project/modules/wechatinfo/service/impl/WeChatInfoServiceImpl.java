package com.company.project.modules.wechatinfo.service.impl;

import com.company.project.core.AbstractService;

import com.company.project.modules.wechatinfo.dao.WeChatInfoMapper;
import com.company.project.modules.wechatinfo.model.WeChatInfo;
import com.company.project.modules.wechatinfo.service.WeChatInfoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author flynacl
 * @date 2018-03-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatInfoServiceImpl extends AbstractService<WeChatInfo> implements WeChatInfoService {
    @Resource
    private WeChatInfoMapper weChatInfoMapper;

}
