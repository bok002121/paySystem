package net.kjk.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_menu")
public class Menu
{
	@Id
	@Column("menu_id")
	private int id;
	
	@Name
	@Column("menu_name")
	private String name;
	
	@Column("menu_url")
	private String url;
	
	@Column("menu_parent")
	private int parent;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getParent()
	{
		return parent;
	}

	public void setParent(int parent)
	{
		this.parent = parent;
	}
	
	
}
