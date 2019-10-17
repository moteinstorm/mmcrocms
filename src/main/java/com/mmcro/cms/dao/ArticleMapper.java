package com.mmcro.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mmcro.cms.entity.Article;

/**
 * 文章管理
 * @author zhuzg
 *
 */
public interface ArticleMapper {

	List<Article> list(@Param("chnId") Integer chnId, 
			@Param("catId") Integer catId);

}
