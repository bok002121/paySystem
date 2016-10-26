package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.UserInfo;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.service.IdNameEntityService;

@IocBean(fields="dao")
public class UserInfoService extends IdNameEntityService<UserInfo>
{
    
	public Boolean add(int id,UserInfo info)
	{
		info.setId(id);
		dao().insert(info);
		System.out.println(Json.toJson(info));
		return true;
	}
	
	public UserInfo fetch(int userid)
	{
		UserInfo i = dao().fetch(UserInfo.class,userid); 
		
		if( i == null)
			return null;
		return i;
	}
	
	public void delete(int userid)
	{
		dao().delete(UserInfo.class,userid);
	}
	
	public List<UserInfo> getAllUser()
	{
		Sql sql = dao().sqls().create("getAllPerson.data");

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

		dao().execute(sql);
		
		return sql.getList(UserInfo.class);
	}
}