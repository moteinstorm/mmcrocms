package com.mmcro.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.entity.Article;

/**
 * 文章管理
 * @author zhuzg
 *
 */
public interface ArticleMapper {

	/**
	 * 频道id必须大于0，分类id可以为0，当分类id为0的时候，查询该频道下的所有分类的文章
	 * 否在查询该分类下的文章
	 * @param chnId  频道id
	 * @param catId  分类id
	 * @return
	 */
	List<Article> list(@Param("chnId") Integer chnId, 
			@Param("catId") Integer catId);

	/**
	 * 获取热门文章
	 * @return
	 */
	List<Article>  listHot();
	/**
	 * 获取最新文章
	 * @param sum
	 * @return
	 */
	List<Article> listLast(int sum);

	/**
	 * 
	 * @param articleId
	 * @return
	 */
	Article findById(Integer articleId);

	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	int add(Article article);

}
