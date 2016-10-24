package net.kjk.nutzbook;

import org.nutz.dao.Dao;

import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup
{
	public void init(NutConfig conf)
	{
		Ioc ioc = conf.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "net.kjk.nutzbook", false);
		
		System.out.println(dao.sqls().count());
//		if(dao.count(User.class) == 0)
//		{
//			UserService us = ioc.get(UserService.class);
//            us.add("admin", "123456");
//		}
		System.out.println("test: 中文");
		
	}
	
	public void destroy(NutConfig conf)
	{
		
	}
}
