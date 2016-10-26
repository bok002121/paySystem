package net.kjk.nutzbook.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User
{
	@Id
	@Column("user_id")
	private int id;
	@Name
	@Column("user_name")
	private String name;
	@Column("user_pwd")
	private String password;
	@Column("user_status")
	private int status;
	@Column
	private Date createtime;
	@Column
	private Date updatetime;
	@One(target=UserInfo.class, field="id", key="id")
    protected UserInfo info;
	
	
	
	public UserInfo getInfo()
	{
		return info;
	}
	public void setInfo(UserInfo info)
	{
		this.info = info;
	}
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
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public Date getUpdatetime()
	{
		return updatetime;
	}
	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}
}
