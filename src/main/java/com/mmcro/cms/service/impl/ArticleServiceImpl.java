package com.mmcro.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmcro.cms.dao.ArticleMapper;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.entity.Comment;
import com.mmcro.cms.entity.Tag;
import com.mmcro.cms.service.ArticleService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service
@Transactional
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

	/**
	 * 获取热门文章
	 */
	@Override
	public PageInfo<Article> hostList(Integer page) {
		//设置页码
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		//查询指定页码数据 并返回页面信息
		return new PageInfo<Article>(articleMapper.listHot()) ;
	}

	/**
	 * 获取最新文章
	 */
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
			//try {
				articleMapper.addArticleTag(article.getId(),tagBean.getId());
			/*}catch(Exception e){
				System.out.println("插入失败 ");
			}*/
		}
	}
	
	
		/**
		 *  修改文章
		 * @param article
		 * @return
		 */
		@Override
		public int update(Article article) {
			// TODO Auto-generated method stub
			int result = articleMapper.update(article);
			// 删除中间表中的
			articleMapper.delTagsByArticleId(article.getId());
			// 处理文章的标签
			processTag(article);
			return result;
			
		}
	
	/**
	 *  根据用户id查找文章列表
	 * @param id 用户id
	 * @param page
	 * @return 
	 */
	@Override
	public PageInfo<Article> listArticleByUserId(Integer userId, Integer page) {
		// TODO Auto-generated method stub
		//设置分页
		PageHelper.startPage(page, 10);
		//返回分页数据
		return new PageInfo<Article>(articleMapper.listByUserId(userId));
	
	}

	/**
	 * 删除文章
	 * @param id  文章id
	 * @return
	 */
	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		int result =  articleMapper.deleteById(id);
		// 删除中间表
		articleMapper.delTagsByArticleId(id);
		return result;
	}

	
	/**
	 * 
	 * @param page 页码
	 * @param status 审核的状态
	 * @return
	 */
	@Override
	public PageInfo<Article> getAdminArticles(Integer page,Integer status) {
		//设置分页信息
		PageHelper.startPage(page, 10);
		return new PageInfo<Article>(articleMapper.listAdmin(status));
	}

	/**
	 *  审核文章
	 * @param articleId
	 * @param status 要审核的状态
	 * @return
	 */
	@Override
	public int updateStatus(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateStatus(articleId,status);
	}

	/**
	 * 
	 *  修改热门
	 * @param articleId
	 * @param status
	 * @return
	 */
	@Override
	public int updateHot(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateHot(articleId,status);
	}

	/**
	 * 发表文章评论
	 * @param id
	 * @param articleId
	 * @param content
	 */
	@Override
	public void comment(Integer userId, Integer articleId, 
			String content) {
		// TODO Auto-generated method stub
		//创建一个评论对象
		Comment comment = new Comment(articleId,userId,content);
		//增加评论
		articleMapper.addComment(comment);
		//评论数量自加
		articleMapper.increaseCommentCnt(articleId);
	}

	/**
	 * 获取评论
	 */
	@Override
	public PageInfo<Comment> getCommentByArticleId(Integer articleId, Integer page) {
		// TODO Auto-generated method stub
		//分页
		PageHelper.startPage(page, 5);
		return new PageInfo<Comment>(articleMapper.getCommnentByArticleId(articleId));
		
	}

	@Override
	public void comment(Integer id, Integer articleId, String content, Date date) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//创建一个评论对象
				Comment comment = new Comment(articleId,id,content);
				comment.setCreated(date);
				//增加评论
				articleMapper.addComment1(comment);
				//评论数量自加
				articleMapper.increaseCommentCnt(articleId);
				
	}

	@Override
	public int addHits(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.increaseHits(id);
	}
	

}
