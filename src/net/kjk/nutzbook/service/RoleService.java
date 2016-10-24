package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.Role;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

@IocBean(fields = "dao")
public class RoleService extends IdNameEntityService<Role>
{
	public MyMenu getMenu(int userId)
	{
		Sql sql = dao().sqls().create("getMenu.data");

		sql.params().set("id", 1);

		sql.setCallback(new SqlCallback()
		{
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException
			{

				MyMenu myMenu = new MyMenu();
				myMenu.setId("1");
				myMenu.setName("欢迎使用");
				myMenu.setUrl("#");
				myMenu.setParent("-1");

				while (rs.next())
				{
					MyMenu m = new MyMenu();

					m.setId(rs.getString("menu_id"));
					m.setName(rs.getString("menu_name"));
					m.setUrl(rs.getString("menu_url"));
					m.setParent(rs.getString("menu_parent"));

					// System.out.println(Json.toJson(m));
					myMenu.addChildMenu(m);
				}
				System.out.println("callback end");
				return myMenu;
			}
		});

		dao().execute(sql);
		System.out.println("execute end");

		MyMenu myMenu = sql.getObject(MyMenu.class);

		return myMenu;
	}
	
}
