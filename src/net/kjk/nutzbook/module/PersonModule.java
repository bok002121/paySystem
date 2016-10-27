package net.kjk.nutzbook.module;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.kjk.nutzbook.bean.Category;
import net.kjk.nutzbook.bean.Department;
import net.kjk.nutzbook.bean.Grade;
import net.kjk.nutzbook.bean.User;
import net.kjk.nutzbook.bean.UserInfo;
import net.kjk.nutzbook.service.UserInfoService;
import net.kjk.nutzbook.service.UserService;
import net.kjk.nutzbook.toolkit.PageToolKit;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
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
		List<Grade> grades = dao.query(Grade.class,null);
		req.setAttribute("grades", grades);
		
		// 获取类别
		List<Category> cates = dao.query(Category.class,null);
		req.setAttribute("cates", cates);
		
		// 获取部门
		List<Department> deps = dao.query(Department.class,null);
		req.setAttribute("deps", deps);
		
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
	
	@At
	@GET
	@Ok("jsp:jsp.person.manage")
	public void manage(HttpServletRequest req)
	{
		// 获取职别
		List<Category> cates = dao.query(Category.class, null);
		req.setAttribute("cates", cates);
		
		// 获取人员并分页。
		int sum = getRecordByData("getPersonSum.data");
        System.out.print(sum);
        
        Sql sql = dao.sqls().create("getAllPerson.data");
		
		Pager pager = dao.createPager(1, PageToolKit.getSize());
		pager.setRecordCount(sum);// 记录数需手动设置
		
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql); 
		System.out.println(Json.toJson(sql.getList(Map.class)));
		req.setAttribute("persons", sql.getList(Map.class));
		
		req.setAttribute("curPage", 1);
		req.setAttribute("sumPage", sum/10 + 1);
	}
	
	@At
	@POST
	public Object changeUserStatus(@Param("id") int id,
			                       @Param("status") String status)
	{
		NutMap re = new NutMap();
		int s = 0;
		String img = "";
		if(status.equals("启动"))
		{
			s = 1;
			status = "禁用";
			img = "image/green.png";
		}
		else
		{
			s = 0;
			status = "启用";
			img = "image/red.png";
		}
		
		Sql sql = dao.sqls().create("changeUserStatus.data");
		sql.setParam("id", id);
		sql.setParam("status",s);
		dao.execute(sql);
		
		if(sql.getUpdateCount() == -1)
		{
			re.setv("ok", false);
		}
		else
		{
			re.setv("ok", true);
			re.setv("status",status);
			re.setv("img", img);
		}
		return re;
	}
	
}
