package net.kjk.nutzbook.service;

import net.kjk.nutzbook.bean.UserInfo;

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
}
