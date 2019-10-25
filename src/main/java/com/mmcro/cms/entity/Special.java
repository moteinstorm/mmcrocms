package com.mmcro.cms.entity;

import java.util.Date;
import java.util.List;

/**
 * 专题
 * @author zhuzg
 *
 */
public class Special  {

	private Integer id;
	private String  title;
	private String  digest;//abstract;
	private Date created;
	
	private Integer articleNum;
	
	public Integer getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}
	
	List<Article> artilceList;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<Article> getArtilceList() {
		return artilceList;
	}
	public void setArtilceList(List<Article> artilceList) {
		this.artilceList = artilceList;
	}
	@Override
	public String toString() {
		return "Special [id=" + id + ", title=" + title + ", digest=" + digest + ", created=" + created
				+ ", articleNum=" + articleNum + ", artilceList=" + artilceList + "]";
	}
	
	
	
}
