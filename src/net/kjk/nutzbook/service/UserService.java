package net.kjk.nutzbook.service;

import net.kjk.nutzbook.bean.Role;
import net.kjk.nutzbook.bean.User;

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
