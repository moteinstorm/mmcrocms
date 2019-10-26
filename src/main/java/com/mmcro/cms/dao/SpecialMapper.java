package com.mmcro.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mmcro.cms.entity.Special;

public interface SpecialMapper {

	/**
	 * 获取专题的列表
	 * @return
	 */
	@Select("SELECT id,title,abstract as digest,created FROM cms_special ORDER BY id desc")
	List<Special> list();

	/**
	 * 添加专题
	 * @param special
	 * @return
	 */
	@Insert("INSERT INTO cms_special (title,abstract,created) VALUES(#{title},#{digest},now() ) ")
	int add(Special special);

	
	@Select("SELECT id,title,abstract as digest,created FROM cms_special WHERE id=#{value} ")
	Special findById(Integer id);

	
	@Insert("INSERT INTO cms_special_article(sid,aid) VALUES(#{sid},#{aid})")
	int addArticle(@Param("sid") Integer specId, @Param("aid") Integer articleId);

	@Delete("DELETE FROM cms_special_article WHERE sid=#{sid} AND aid=#{aid}")
	int removeArticle(@Param("sid") Integer specId, 
			@Param("aid")  Integer articleId);

	/**
	 * 修改专题
	 * @param special
	 * @return
	 */
	@Update("UPDATE cms_special SET title=#{title},abstract=#{digest} "
			+ " WHERE id=#{id} ")
	int update(Special special);
	

}
