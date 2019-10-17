package com.mmcro.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmcro.cms.dao.CatMapper;
import com.mmcro.cms.entity.Cat;
import com.mmcro.cms.service.CatService;

/**
 * 
 * @author zhuzg
 *
 */
@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	CatMapper catMapper;

	/**
	 * 根据频道去获取下边的分类
	 * @param id
	 * @return
	 */
	@Override
	public List<Cat> getListByChnlId(Integer id) {
		// TODO Auto-generated method stub
		return catMapper.selectByChnlId(id);
	}
	

}
