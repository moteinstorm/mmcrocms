package com.mmcro.cms.entity;

import java.io.Serializable;

/**
 *  文章的标签
 * @author zhuzg
 *
 */
public class Tag implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5879066127875281505L;
	
	Integer id;
	String tagname;
	
	
	public Tag(String tagname) {
		super();
		this.tagname = tagname;
	}
	
	public Tag() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tagname == null) ? 0 : tagname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tagname == null) {
			if (other.tagname != null)
				return false;
		} else if (!tagname.equals(other.tagname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagname=" + tagname + "]";
	}
	
	

}
