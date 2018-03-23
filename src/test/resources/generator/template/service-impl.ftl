package ${targetPrePath}.service.impl;

import ${basePackage}.core.AbstractService;

import ${targetPrePath}.dao.${modelNameUpperCamel}Mapper;
import ${targetPrePath}.model.${modelNameUpperCamel};
import ${targetPrePath}.service.${modelNameUpperCamel}Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ${author}
 * @date ${date}
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
