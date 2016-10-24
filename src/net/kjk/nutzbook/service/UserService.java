package net.kjk.nutzbook.service;

import java.util.Date;

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
	
	public int add(String name,String psw){
		User u = new User();
		
		u.setName(name);
		u.setPassword(psw);
		u.setStatus(1);
		u.setCreatetime(new Date());
		u.setUpdatetime(new Date());
		
		User o = dao().insert(u);
		
		return o.getId();
	}
}
