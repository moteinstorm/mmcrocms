package com.mmcro.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmcro.cms.dao.ChannelMapper;
import com.mmcro.cms.entity.Channel;
import com.mmcro.cms.service.ChannelService;

/**
 * 
 * @author zhuzg
 *
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	
	/**
	 *  获取所有的频道（栏目）
	 * @return
	 */
	@Override
	public List<Channel> getAllChnls() {
		// TODO Auto-generated method stub
		return channelMapper.listAll();
	
	}

}
