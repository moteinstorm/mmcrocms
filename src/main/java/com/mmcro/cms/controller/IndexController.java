package com.mmcro.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.entity.Cat;
import com.mmcro.cms.entity.Channel;
import com.mmcro.cms.service.ArticleService;
import com.mmcro.cms.service.CatService;
import com.mmcro.cms.service.ChannelService;

/**
 * 
 * @author zhuzg
 *
 */
@Controller
public class IndexController {

	@Autowired
	ChannelService chnlService;
	
	@Autowired
	CatService catService;
	
	@Autowired
	ArticleService articleService;

	@RequestMapping("index")
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="0") Integer chnId,
			@RequestParam(defaultValue="0")  Integer catId,
			@RequestParam(defaultValue="1")  Integer page
			) {

		// 获取所有的频道
		List<Channel> channels = chnlService.getAllChnls();
		if(chnId!=0) {
			//获取该栏目下的所有分类
			List<Cat> catygories = catService.getListByChnlId(chnId); 
			request.setAttribute("catygories", catygories);
			PageInfo<Article>  articleList = articleService.list(chnId,catId,page);
			request.setAttribute("articles", articleList);
			
		}
		
		request.setAttribute("chnls", channels);
		
		return "index";
	}

}
