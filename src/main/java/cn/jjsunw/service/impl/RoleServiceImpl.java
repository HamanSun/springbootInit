package cn.jjsunw.service.impl;

import cn.jjsunw.mapper.RoleMapper;
import cn.jjsunw.model.Role;
import cn.jjsunw.service.RoleService;
import cn.jjsunw.common.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by jjsunw on 2018/11/28.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper tRoleMapper;

}
