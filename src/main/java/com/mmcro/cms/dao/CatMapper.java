package com.mmcro.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mmcro.cms.entity.Cat;

@Mapper
public interface CatMapper {

	@Select("SELECT id,name,channel_id channelId "
			+ " FROM cms_category "
			+ " WHERE channel_id=#{value}")
	List<Cat> selectByChnlId(Integer id);

}