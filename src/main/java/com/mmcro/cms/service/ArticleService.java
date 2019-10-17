package com.mmcro.cms.service;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.entity.Article;


public interface ArticleService {
	
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

}
