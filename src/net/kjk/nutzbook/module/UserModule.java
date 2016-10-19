package net.kjk.nutzbook.module;

import javax.servlet.http.HttpSession;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.User;
import net.kjk.nutzbook.service.RoleService;
import net.kjk.nutzbook.service.UserService;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("/user")
public class UserModule extends BaseModule
{
	@Inject protected UserService userService;
	@Inject protected RoleService roleService;
	
	@At("/")
    @Ok("jsp:jsp.user.welcome") // 真实路径是 /WEB-INF/jsp/user/welcome.jsp
    public void index() {
    }
	
	@At
	@POST
	@Filters()
	public Object login(@Param("username") String name,
			@Param("password") String password, HttpSession session)
	{
		NutMap re = new NutMap();
		
		// 验证账号密码
		User user = userService.fetch(name,password);
		
		if(user == null)
		{
			return re.setv("ok", false).setv("msg", "账号或密码错误");
		}
		if(user.getStatus() != 1)
		{
			return re.setv("ok", false).setv("msg", "用户被禁用");
		}
		else
		{
			// 获取菜单
			MyMenu menu = roleService.getMenu(user.getId());
			
			// 获取权限
			
			// 设置 session
			
			// 返回
			return re.setv("ok",true).setv("msg", "good");
		}
	}
	
	@At
    @Ok("jsp:jsp.user.header") // 真实路径是 /WEB-INF/jsp/user/header.jsp
    public void header() {
    }
	
	@At
    @Ok("jsp:jsp.user.menu") // 真实路径是 /WEB-INF/jsp/user/menu.jsp
    public void menu() {
    }
	
	@At
    @Ok("jsp:jsp.user.main") // 真实路径是 /WEB-INF/jsp/user/main.jsp
    public void main() {
    }
}
