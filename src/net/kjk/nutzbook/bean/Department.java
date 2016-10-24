package net.kjk.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_departemnt")
public class Department
{
	@Id
	@Column("dep_id")
	private int depId;
	
	@Name
	@Column("dep_name")
	private String depName;

	public int getDepId()
	{
		return depId;
	}

	public void setDepId(int depId)
	{
		this.depId = depId;
	}

	public String getDepName()
	{
		return depName;
	}

	public void setDepName(String depName)
	{
		this.depName = depName;
	}
	
	
}
