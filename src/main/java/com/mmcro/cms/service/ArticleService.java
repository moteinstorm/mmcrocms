package com.mmcro.cms.service;

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

}
