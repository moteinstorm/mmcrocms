package com.mmcro.cms.service;

import com.mmcro.cms.entity.User;

public interface UserService {
	
	int register(User user) ;
	User login(User user);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);
	

}
