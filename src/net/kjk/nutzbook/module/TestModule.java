package net.kjk.nutzbook.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import net.kjk.nutzbook.bean.Role;
import net.kjk.nutzbook.bean.MyMenu;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
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
		//dao.fetchLinks(role, "menus");
		
		System.out.println(Json.toJson(role));
	}
	
	@At
	public void daoSqlTest()
	{
		Sql sql = Sqls.create("select menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menut_id;");
		sql.params().set("id", 1);
		sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next())
	                list.add(rs.getString("menu_name"));
	            System.out.println(Json.toJson(list));
	            return true;
	        }
	    });
		
		
		dao.execute(sql);
	}
	
	@At
	public void daoSql2Test()
	{
		Sql sql = Sqls.queryRecord("select distinct(menu_id) menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menut_id;");
		sql.params().set("id", 1);
		
		dao.execute(sql);
		List<String> list = new LinkedList<String>();
		List<Record> rs = sql.getList(Record.class);
		for (Record re : rs) {
			list.add(re.getString("menu_name"));
		  // 继续你想做的事
		}
		System.out.println(Json.toJson(list));
	}
	
	@At
	public void getMenu()
	{
		Sql sql = Sqls.queryRecord("select distinct(menu_id) menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menut_id;");
		sql.params().set("id", 1);
		
		dao.execute(sql);
		
		MyMenu myMenu = new MyMenu();
		myMenu.setName("欢迎使用");
		myMenu.setUrl("#");
		
		
		List<Record> results = sql.getList(Record.class);
		for (Record rs : results) {
			rs.getString("menu_name");
		  // 继续你想做的事
		}
	}
}