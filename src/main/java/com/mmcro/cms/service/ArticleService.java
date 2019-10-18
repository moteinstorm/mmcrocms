package com.mmcro.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.entity.Article;


public interface ArticleService {
	
	/**
	 * 
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @param page  页码
	 * @return
	 */
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

	/**
	 * 
	 * @param page
	 * @return
	 */
	PageInfo<Article> hostList( Integer page);

	/**
	 * 获取最新文章
	 * @param sum  获取的数目
	 * @return
	 */
	List<Article> last(int sum);

	/**
	 * 根据文章的主键获取文章的内容
	 * @param articleId
	 * @return
	 */
	Article findById(Integer articleId);

	int add(Article article);
	

}
