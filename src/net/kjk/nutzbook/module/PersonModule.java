package net.kjk.nutzbook.module;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

@At("/person")
public class PersonModule extends BaseModule
{
	@At
	@GET
	@Ok("jsp:jsp.person.add")
	public void add(HttpServletRequest req)
	{
		// 获取职别
		
		
		// 获取类别
		
		// 获取部门
		
		
	}
}
