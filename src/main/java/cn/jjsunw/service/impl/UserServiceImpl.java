package cn.jjsunw.service.impl;

import cn.jjsunw.mapper.UserMapper;
import cn.jjsunw.model.User;
import cn.jjsunw.service.UserService;
import cn.jjsunw.common.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by jjsunw on 2018/11/28.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper tUserMapper;

}
