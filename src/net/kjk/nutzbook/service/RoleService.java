package net.kjk.nutzbook.service;

import java.util.List;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.Role;

import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

@IocBean(fields="dao")
public class RoleService extends IdNameEntityService<Role>
{
	public MyMenu getMenu(int userId)
	{
		Sql sql = Sqls.queryRecord("select distinct(t_menu.menu_id),menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menu_id " + 
				"order by menu_grade asc,menu_id asc;");
		sql.params().set("id", 1);
		
		dao().execute(sql);
		
		MyMenu myMenu = new MyMenu();
		myMenu.setId("1");
		myMenu.setName("欢迎使用");
		myMenu.setUrl("#");
		myMenu.setParent("-1");
		
		//Map<String,MyMenu> menus;
		
		List<Record> results = sql.getList(Record.class);
		for (Record rs : results) {
			MyMenu m = new MyMenu();
			
			m.setId(rs.getString("menu_id"));
			m.setName(rs.getString("menu_name"));
			m.setUrl(rs.getString("menu_url"));
			m.setParent(rs.getString("menu_parent"));
			
			//System.out.println(Json.toJson(m));
			myMenu.addChildMenu(m);
		}
		
		return myMenu;
	}
}
