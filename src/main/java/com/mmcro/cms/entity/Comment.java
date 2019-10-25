package com.mmcro.cms.entity;

import java.util.Date;

/**
 * 评论
 * @author zhuzg
 *
 */
public class Comment {
	
	private Integer id;
	private Integer articleId;
	private Integer userId;
	private String userName;
	private String  content;
	private Date  created;
	
	public Comment() {
		
	}
	
	public Comment(Integer articleId, Integer userId, String content) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.content = content;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getArticleId() {
		return articleId;
	}


	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", userId=" + userId + ", userName=" + userName
				+ ", content=" + content + ", created=" + created + "]";
	}
	
	
	
}
