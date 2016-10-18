package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import net.kjk.nutzbook.bean.Role;
import net.kjk.nutzbook.bean.User;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;



@IocBean(fields="dao")
public class UserService extends IdNameEntityService<User>
{
	public User fetch(String username, String password) {
        User user = fetch(username);
        if (user == null) {
            return null;
        }
        String _pass = password;
        if(_pass.equalsIgnoreCase(user.getPassword())) {
            return user;
        }
        return null;
    }
	
	public int InitUser(int userId)
	{
		Role role = dao().fetch(Role.class,userId);
		return 1;
	}
}
