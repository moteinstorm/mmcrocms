package com.mmcro.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mmcro.cms.entity.Channel;

public interface ChannelMapper {

	/**
	 * 获取所有的频道
	 * @return
	 */
	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> listAll();

}
