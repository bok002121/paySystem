package net.kjk.nutzbook.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user_info")
public class UserInfo
{
	@Id(auto=false)
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
	private String idCard;
	
	@Column("join_time")
	private Date joinTime;
	
	@Column("grade_id")
	private int gradeId;
	
	@Column("cate_id")
	private int cateId;
	
	@Column("ic_card")
	private String icCard;
	
	@Column("dep_id")
	private int depId;
	
	@Column("job_no")
	private String jobNo;
	
	public UserInfo(){}

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

	public String getIdCard()
	{
		return idCard;
	}

	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}

	public Date getJoinTime()
	{
		return joinTime;
	}

	public void setJoinTime(Date joinTime)
	{
		this.joinTime = joinTime;
	}

	public int getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(int gradeId)
	{
		this.gradeId = gradeId;
	}

	public int getCateId()
	{
		return cateId;
	}

	public void setCateId(int cateId)
	{
		this.cateId = cateId;
	}

	public String getIcCard()
	{
		return icCard;
	}

	public void setIcCard(String icCard)
	{
		this.icCard = icCard;
	}

	public int getDepId()
	{
		return depId;
	}

	public void setDepId(int depId)
	{
		this.depId = depId;
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
