package cn.jjsunw.mapper;

import org.apache.ibatis.annotations.Param;

import cn.jjsunw.common.SystemMapper;
import cn.jjsunw.model.User;

public interface UserMapper extends SystemMapper<User> {
	User findByUsername(@Param("username") String username);
}