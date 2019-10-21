package com.mmcro.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.service.ArticleService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	ArticleService articelService;
	
	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("manArticle")
	public String adminArticle(HttpServletRequest request,
			@RequestParam(defaultValue="1") Integer page
			) {
			
		  PageInfo<Article> pageInfo= articelService.getAdminArticles(page);
		  request.setAttribute("pageInfo", pageInfo);
		  
			return "admin/article/list";
		
	}
	
	@RequestMapping("getArticle")
	public String getArticle(HttpServletRequest request,Integer id) {
		Article article = articelService.findById(id);
		request.setAttribute("article", article);
		return "admin/article/detail";
		
		
	}

}
