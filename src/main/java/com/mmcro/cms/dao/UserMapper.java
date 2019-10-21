package com.mmcro.cms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mmcro.cms.entity.User;

/**
 * 
 * @author zhuzg
 *
 */
public interface UserMapper {

	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@Insert("INSERT INTO cms_user(username,password,gender,create_time) "
			+ "values(#{username},#{password},#{gender},now())")
	int add(User user);
	
	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	@Select("SELECT id,username,password,role FROM cms_user "
			+ "WHERE username=#{value} limit 1")
	User findByName(String username);
	

}
