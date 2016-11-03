package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.UserInfo;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.service.IdNameEntityService;

@IocBean
public class UserInfoService extends BaseService
{
    
	public Boolean add(int id,UserInfo info)
	{
		info.setId(id);
		dao.insert(info);
		System.out.println(Json.toJson(info));
		return true;
	}
	
	public UserInfo fetch(int userid)
	{
		UserInfo i = dao.fetch(UserInfo.class,userid); 
		
		if( i == null)
			return null;
		return i;
	}
	
	public void delete(int userid)
	{
		dao.delete(UserInfo.class,userid);
	}
	
	public List<UserInfo> getAllUser()
	{
		Sql sql = dao.sqls().create("getAllPerson.data");

		sql.setCallback(new SqlCallback()
		{
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException
			{

				List<UserInfo> infos = new ArrayList<UserInfo>();;
				while (rs.next())
				{
					UserInfo i = new UserInfo();
					
					i.setId(rs.getInt("user_id"));

				    infos.add(i);
				}
				System.out.println(Json.toJson(infos));
				return infos;
			}
		});

		dao.execute(sql);
		
		return sql.getList(UserInfo.class);
	}
	
	
	/*
	 * 0 - 姓名      1 - 性别   2 - 出生年月
	* 3 - 身份证  4 - 参加工作时间  5 - 职别
	* 6 - 类别  7 - 银行卡账号  8 - 部门
	* 9 - 工号
	* -1 则更新失败
	 */
	public int insertListList(List<List<String>> contents,Map<String,String>field)
	{
		if(contents.size() > 0)
		{
			String sqlStr = "insert into t_user_info(user_id,name," +
					"sex,birthday,id_card,join_time,grade_id,cate_id,ic_card,dep_id,job_no) VALUES ";
			
			int idIndex = Integer.parseInt(field.get("user_id"));
			int nameIndex = Integer.parseInt(field.get("name"));
			int sexIndex = Integer.parseInt(field.get("sex"));
			int birthIndex = Integer.parseInt(field.get("birthday"));
			int idCardIndex = Integer.parseInt(field.get("id_card"));
			int joinIndex = Integer.parseInt(field.get("join_time"));
			int gradeIndex = Integer.parseInt(field.get("grade_name"));
			int cateIndex = Integer.parseInt(field.get("cate_name"));
			int icCardIndex = Integer.parseInt(field.get("ic_card"));
			int depIndex = Integer.parseInt(field.get("dep_name"));
			int jobNoIndex = Integer.parseInt(field.get("job_no"));
			String t = "";
			
			for( int i = 0; i< contents.size(); i++)
			{
				t = "(";
				t += contents.get(i).get(idIndex) +",'";
				t += contents.get(i).get(nameIndex) + "','";
				t += contents.get(i).get(sexIndex) + "','";
				t += contents.get(i).get(birthIndex) + "','";
				t += contents.get(i).get(idCardIndex) + "','";
				t += contents.get(i).get(joinIndex) + "',";
				t += contents.get(i).get(gradeIndex) + ",";
				t += contents.get(i).get(cateIndex) + ",'";
				t += contents.get(i).get(icCardIndex) + "',";
				t += contents.get(i).get(depIndex) + ",'";
				t += contents.get(i).get(jobNoIndex) + "')";
				
				if( i < contents.size() - 1)
					t +=",";
				sqlStr += t;
			}
			System.out.println(sqlStr);
			
			Sql sql = Sqls.create(sqlStr);
			dao.execute(sql);
			
			return sql.getUpdateCount();
		}
		return 0;
	}
	
	/*
	 * 0 - 姓名      1 - 性别   2 - 出生年月
	* 3 - 身份证  4 - 参加工作时间  5 - 职别
	* 6 - 类别  7 - 银行卡账号  8 - 部门
	* 9 - 工号  10 - id
	 */
	public int updateListList(List<List<String>> contents,Map<String,String> field)
	{
        if(contents.size() > 0)
        {
        	String sqlStr = "insert into t_user_info(user_id,name,sex,birthday," +
            		"id_card,join_time,grade_id,cate_id,ic_card,dep_id,job_no) VALUES ";
    		
    		int idIndex = Integer.parseInt(field.get("user_id"));
    		int nameIndex = Integer.parseInt(field.get("name"));
    		int sexIndex = Integer.parseInt(field.get("sex"));
    		int birthIndex = Integer.parseInt(field.get("birthday"));
    		int idCardIndex = Integer.parseInt(field.get("id_card"));
    		int joinIndex = Integer.parseInt(field.get("join_time"));
    		int gradeIndex = Integer.parseInt(field.get("grade_name"));
    		int cateIndex = Integer.parseInt(field.get("cate_name"));
    		int icCardIndex = Integer.parseInt(field.get("ic_card"));
    		int depIndex = Integer.parseInt(field.get("dep_name"));
    		int jobNoIndex = Integer.parseInt(field.get("job_no"));
    		String t = "";
    		
    		for( int i = 0; i< contents.size(); i++)
    		{
    			t = "(";
    			t += contents.get(i).get(idIndex) +",'";
    			t += contents.get(i).get(nameIndex) + "','";
    			t += contents.get(i).get(sexIndex) + "','";
    			t += contents.get(i).get(birthIndex) + "','";
    			t += contents.get(i).get(idCardIndex) + "','";
    			t += contents.get(i).get(joinIndex) + "',";
    			t += contents.get(i).get(gradeIndex) + ",";
    			t += contents.get(i).get(cateIndex) + ",'";
    			t += contents.get(i).get(icCardIndex) + "',";
    			t += contents.get(i).get(depIndex) + ",'";
    			t += contents.get(i).get(jobNoIndex) + "')";
    			
    			if( i < contents.size() - 1)
    				t +=",";
    			sqlStr += t;
    		}
    		
    		sqlStr += " on duplicate key update " +
    				"name = values(name)," +
    				"sex = values(sex),birthday = values(birthday)," +
    				"id_card = values(id_card),join_time = values(join_time)," +
    				"grade_id = values(grade_id),cate_id = values(cate_id)," +
    				"ic_card = values(ic_card),dep_id = values(dep_id),job_no = values(job_no)";
    		
    		Sql sql = Sqls.create(sqlStr);
    		
    		System.out.println(sqlStr);
    		dao.execute(sql);
    		
    		return sql.getUpdateCount();
        }
        return 0;
	}
}
