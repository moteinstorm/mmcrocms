package com.mmcro.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmcro.cms.dao.ArticleMapper;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.entity.Tag;
import com.mmcro.cms.service.ArticleService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleMapper articleMapper;

	/**
	 * 
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @param page  页码
	 * @return
	 */
	@Override
	public PageInfo<Article> list(Integer chnId, 
			Integer catId, Integer page) {
		//设置页码
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		//查询指定页码数据 并返回页面信息
		return new PageInfo(articleMapper.list(chnId,catId)) ;
	}

	@Override
	public PageInfo<Article> hostList(Integer page) {
		// TODO Auto-generated method stub
		//设置页码
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		//查询指定页码数据 并返回页面信息
		return new PageInfo(articleMapper.listHot()) ;
	}

	@Override
	public List<Article> last(int sum) {
		// TODO Auto-generated method stub
		return  articleMapper.listLast(sum);
	}

	@Override
	public Article findById(Integer articleId) {
		// TODO Auto-generated method stub
		return articleMapper.findById(articleId);
				
	}

	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		
		int result =  articleMapper.add(article);
		processTag(article);
		
		return result ;
	}
	
	/**
	 *  处理文章的标签
	 * @param article
	 */
	private void processTag(Article article){
		
		if(article.getTags()==null)
			return;
		
		String[] tags = article.getTags().split(",");
		for (String tag : tags) {
			// 判断这个tag在数据库当中是否存在
			Tag tagBean = articleMapper.findTagByName(tag);
			if(tagBean==null) {
				tagBean = new Tag(tag);
				articleMapper.addTag(tagBean);
			}
			
			//插入中间表
			try {
				articleMapper.addArticleTag(article.getId(),tagBean.getId());
			}catch(Exception e){
				System.out.println("插入失败 ");
			}
		}
	}
	
	

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		int result = articleMapper.update(article);
		// 删除中间表中的
		articleMapper.delTagsByArticleId(article.getId());
		processTag(article);
		return result;
		
	}
	
	/**
	 * 
	 */
	@Override
	public PageInfo<Article> listArticleByUserId(Integer userId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);
		return new PageInfo<Article>(articleMapper.listByUserId(userId));
	
	}

	
	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		int result =  articleMapper.deleteById(id);
		// 删除中间表
		articleMapper.delTagsByArticleId(id);
		return result;
	}

	

	@Override
	public PageInfo<Article> getAdminArticles(Integer page,Integer status) {
		// TODO Auto-generated method stub
		System.out.println(" ============ page is " + page);
		PageHelper.startPage(page, 10);
		return new PageInfo<Article>(articleMapper.listAdmin(status));
	}

	@Override
	public int updateStatus(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateStatus(articleId,status);
	}

	@Override
	public int updateHot(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateHot(articleId,status);
	}
	

}
