package net.kjk.nutzbook.module;

import net.kjk.nutzbook.bean.Role;

import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;

@IocBean
@Filters()
public class TestModule extends BaseModule
{
	@At
	public void daoTest()
	{
		/*
		 * 如果不用POJO的话，那么那些字段一定要对应数据库的字段
		 */
		Record re = dao.fetch("t_user", Cnd.where("user_id","=",1));
		
		System.out.println(re.getString("user_name"));
		
		/**
		 * 用下面这种得花，要注意用 @Table 注解好那个类
		 */
//		User user = dao.fetch(User.class,
//				Cnd.where("id", "=", 1));
//		
//		System.out.println("???" + user.getName());
	}
	
	@At
	public void daoManyTest()
	{
		Role role = dao.fetch(Role.class,1);
		dao.fetchLinks(role, "menus");
		
		System.out.println(Json.toJson(role));
	}
}
