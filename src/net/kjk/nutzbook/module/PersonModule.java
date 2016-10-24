package net.kjk.nutzbook.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.kjk.nutzbook.bean.UserInfo;
import net.kjk.nutzbook.service.UserInfoService;
import net.kjk.nutzbook.service.UserService;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/person")
@IocBean
public class PersonModule extends BaseModule
{
	@Inject protected UserService userService;
	@Inject protected UserInfoService userInfoService;
	@At
	@GET
	@Ok("jsp:jsp.person.add")
	public void add(HttpServletRequest req)
	{
		// 获取职别
		
		
		// 获取类别
		
		// 获取部门
		
		
	}
	
	@At
	@POST
	@Aop(TransAop.READ_COMMITTED)
	public Object add(@Param("..") UserInfo info,HttpSession session)
	{
		NutMap re = new NutMap();
		// 获取传进来的值	
		String psw = info.getIdCard().substring(6,14);
		int newUserId = userService.add(info.getIdCard(), psw);
		System.out.println(newUserId);
		userInfoService.add(newUserId,info);
		
		return re.setv("ok", true);
	}
}
