package com.mmcro.cms.service;


import java.util.List;

import com.mmcro.cms.entity.Channel;

/**
 * 
 * @author zhuzg
 *
 */
public interface ChannelService {

	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	List<Channel> getAllChnls();

}
