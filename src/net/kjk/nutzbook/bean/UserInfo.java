package net.kjk.nutzbook.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class UserInfo
{
	@Id
	@Column("user_id")
	private int id;
	
	@Name
	@Column("name")
	private String name;
	
	@Column("sex")
	private String sex;
	
	@Column("birthday")
	private Date birthday;
	
	@Column("id_card")
	private String id_card;
	
	@Column("join_time")
	private String join_time;
	
	@Column("grade_id")
	private int grade_id;
	
	@Column("cate_id")
	private int cate_id;
	
	@Column("ic_card")
	private String ic_card;
	
	@Column("dep_id")
	private int dep_id;
	
	@Column("jobno")
	private String jobNo;

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

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getId_card()
	{
		return id_card;
	}

	public void setId_card(String id_card)
	{
		this.id_card = id_card;
	}

	public String getJoin_time()
	{
		return join_time;
	}

	public void setJoin_time(String join_time)
	{
		this.join_time = join_time;
	}

	public int getGrade_id()
	{
		return grade_id;
	}

	public void setGrade_id(int grade_id)
	{
		this.grade_id = grade_id;
	}

	public int getCate_id()
	{
		return cate_id;
	}

	public void setCate_id(int cate_id)
	{
		this.cate_id = cate_id;
	}

	public String getIc_card()
	{
		return ic_card;
	}

	public void setIc_card(String ic_card)
	{
		this.ic_card = ic_card;
	}

	public int getDep_id()
	{
		return dep_id;
	}

	public void setDep_id(int dep_id)
	{
		this.dep_id = dep_id;
	}

	public String getJobNo()
	{
		return jobNo;
	}

	public void setJobNo(String jobNo)
	{
		this.jobNo = jobNo;
	}
	
	
	
}
