package com.mmcro.cms.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.comon.ArticleType;
import com.mmcro.cms.entity.Article;

public class TestArticle  extends BaseTest{
	
	@Autowired
	ArticleService arService;
	
	@Test
	public void testList() {
		int chnId= 3;
		PageInfo<Article> list = arService.list(3, 0, 1);
		if(list!=null && list.getList() != null) {
			list.getList().forEach(article->{
				System.out.println("article is " + article );
			});
		}
		
	} 
	
	
	@Test
	public void testGetAarticle() {
		
		System.out.println(" 33  文章是 ： " +  arService.findById(33).toString());
		
		System.out.println(" 34  文章是 ： " + arService.findById(34).toString());
		
	} 
	
	@Test
	public void testAddAarticle() {
		
		Article article1 = new Article();
		article1.setArticleType(ArticleType.HTML);
		article1.setTitle("测试html 文章");
		arService.add(article1);
		
		
		Article article2 = new Article();
		article2.setArticleType(ArticleType.IMAGE);
		article2.setTitle("测试html 文章");
		arService.add(article2);
		
	} 
	

}
