package net.kjk.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_grade")
public class Grade
{
	@Id
	@Column("grade_id")
	private int gradeId;
	
	@Name
	@Column("grade_name")
	private String gradeName;

	public int getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(int gradeId)
	{
		this.gradeId = gradeId;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}
	
	
}
