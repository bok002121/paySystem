package net.kjk.nutzbook.module;

import javax.servlet.http.HttpSession;

import net.kjk.nutzbook.bean.User;
import net.kjk.nutzbook.service.UserService;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@IocBean
public class UserModule extends BaseModule
{
	@Inject protected UserService userService;
	
	@At
	@POST
	public Object login(@Param("username") String name,
			@Param("password") String password, HttpSession session)
	{
		NutMap re = new NutMap();
		
		// 验证账号密码
		User user = userService.fetch(name,password);
		
		if(user == null)
		{
			// 密码错误
		}
		else
		{
			// 获取角色
			
			// 获取菜单
			
			// 获取权限
		}
		
		
		return re.setv("ok", false);
	}
}
