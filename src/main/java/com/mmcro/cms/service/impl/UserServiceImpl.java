package com.mmcro.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmcro.cms.dao.UserMapper;
import com.mmcro.cms.entity.User;
import com.mmcro.cms.service.UserService;
import com.mmcro.utils.Md5Utils;

/**
 * 
 * @author zhuzg
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	

	/**
	 * 注册
	 */
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.findByName(user.getUsername());
		if(existUser!=null) {
			return -1;// 用户已经存在
		}
		//设置密码密文
		user.setPassword(
				Md5Utils.md5(user.getPassword(),user.getUsername()));
		
		return userMapper.add(user);
		
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String pwdStr = Md5Utils.md5(user.getPassword(),user.getUsername());
		User loginUser =  userMapper.findByName(user.getUsername());
		if(loginUser!=null && pwdStr.equals(loginUser.getPassword())) {
			return loginUser;
		}
		return null;
	}

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	@Override
	public boolean checkUserExist(String username) {
		// TODO Auto-generated method stub
		return null!=userMapper.findByName(username);
		
	}

}
