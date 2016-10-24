package net.kjk.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_category")
public class Category
{
	@Id
	@Column("cate_id")
	private int cateId;
	
	@Name
	@Column("cate_name")
	private String cateName;

	public int getCateId()
	{
		return cateId;
	}

	public void setCateId(int cateId)
	{
		this.cateId = cateId;
	}

	public String getCateName()
	{
		return cateName;
	}

	public void setCateName(String cateName)
	{
		this.cateName = cateName;
	}
	
	
}
