package com.mmcro.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmcro.cms.dao.ArticleMapper;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleMapper articleMapper;

	@Override
	public PageInfo<Article> list(Integer chnId, Integer catId, Integer page) {
		
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		return new PageInfo(articleMapper.list(chnId,catId)) ;
	}

}
