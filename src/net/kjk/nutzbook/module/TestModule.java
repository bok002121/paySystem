package net.kjk.nutzbook.module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.kjk.nutzbook.bean.MyMenu;
import net.kjk.nutzbook.bean.Role;
import net.kjk.nutzbook.bean.UserInfo;
import net.kjk.nutzbook.service.UserInfoService;
import net.kjk.nutzbook.toolkit.PageToolKit;

import org.nutz.dao.Cnd;
import org.nutz.dao.DaoException;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.FieldMeta;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@Filters()
@At("/test")
public class TestModule extends BaseModule
{
	@Inject protected UserInfoService userInfoService;
	
	@At
	public void fenye(){
		
	}
	
	
	
	@At
	public void daoTest()
	{
		/*
		 * 如果不用POJO的话，那么那些字段一定要对应数据库的字段
		 */
		Record re = dao.fetch("t_user", Cnd.where("user_id","=",1));
		
		System.out.println(re.getString("user_name"));
		
		/**
		 * 用下面这种得花，要注意用 @Table 注解好那个类
		 */
//		User user = dao.fetch(User.class,
//				Cnd.where("id", "=", 1));
//		
//		System.out.println("???" + user.getName());
	}
	
	@At
	@Ok("jsp:jsp.test.f")
	public void f(){
		
	}
	
	@At
	@AdaptBy(type=UploadAdaptor.class, args={"${app.root}/WEB-INF/tmp", "8192", "utf-8", "20000", "102400"})
    @POST
    public void inPerson(@Param("Filedata")TempFile tf,
    		HttpServletRequest req,  
            AdaptorErrorContext err) throws IOException
	{
		String msg = null;
        if (err != null && err.getAdaptorErr() != null) {
            msg = "size not good";
        } else if (tf == null) {
            msg = "empty";
        } else {
            try {
            	msg = "good";
            	
            	File f = tf.getFile();                       
            	// 这个是保存的临时文件   
            	FieldMeta meta = tf.getMeta();               
            	// 这个原本的文件信息   
            	String oldName = meta.getFileLocalName();
            	
            	System.out.print(oldName);
            	
            } catch(DaoException e) {
                msg = "system error";
            } catch (Throwable e) {
            	msg = "system error";
            }
        }
        System.out.print(msg);
        
	}
	
	@At
	public void changeTest()
	{
	
		Sql sql = dao.sqls().create("changeUserStatus.data");
		sql.setParam("id", 1);
		sql.setParam("status",0);
		
		//sql.setCallback(Sqls.callback.integer());
		
		dao.execute(sql);
		
		//System.out.println(Json.toJson(sql.getObject(Integer.class)));
		System.out.println(Json.toJson(sql.getUpdateCount()));
	}
	
	@At
	public void daoManyTest()
	{
		Role role = dao.fetch(Role.class,1);
		//dao.fetchLinks(role, "menus");
		
		System.out.println(Json.toJson(role));
	}
	
	@At
	public void daoSqlTest()
	{
		Sql sql = Sqls.create("select menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menut_id;");
		sql.params().set("id", 1);
		sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next())
	                list.add(rs.getString("menu_name"));
	            System.out.println(Json.toJson(list));
	            return true;
	        }
	    });
		
		
		dao.execute(sql);
	}
	
	@At
	public void daoSql2Test()
	{
		Sql sql = Sqls.queryRecord("select distinct(t_menu.menu_id),menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menu_id " + 
				"order by menu_grade asc,menu_id asc;");
		sql.params().set("id", 1);
		
		dao.execute(sql);
		List<String> list = new LinkedList<String>();
		List<Record> rs = sql.getList(Record.class);
		for (Record re : rs) {
			list.add(re.getString("menu_id"));
			list.add(re.getString("menu_name"));
			list.add(re.getString("menu_url"));
			list.add(re.getString("menu_parent"));
		  // 继续你想做的事
		}
		System.out.println(Json.toJson(list));
	}
	
	@At
	@Ok("jsp:jsp.test.getMenu")
	public void getMenu(HttpSession session)
	{
		Sql sql = Sqls.queryRecord("select distinct(t_menu.menu_id),menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menu_id " + 
				"order by menu_grade asc,menu_id asc;");
		sql.params().set("id", 1);
		
		dao.execute(sql);
		
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
		
		session.setAttribute("menus", myMenu);
	}
	
	@At
	@Ok("jsp:jsp.test.getMenuDi")
	public void getMenuDi(HttpSession session)
	{
		Sql sql = Sqls.queryRecord("select distinct(t_menu.menu_id),menu_name,menu_url,menu_parent " +
				"from t_user_group,t_group_menu,t_menu " +
				"where t_user_group.user_id=@id " +
				"and t_group_menu.group_id = t_user_group.group_id " +
				"and t_group_menu.menu_id = t_menu.menu_id " + 
				"order by menu_grade asc,menu_id asc;");
		sql.params().set("id", 1);
		
		dao.execute(sql);
		
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
		
		session.setAttribute("menus", myMenu);
	}
	
	@At
	@Ok("jsp:jsp.test.getMenuDi")
	public void getMenuByIoc(HttpSession session)
	{
		Sql sql = dao.sqls().create("getMenu.data");
		
		sql.params().set("id", 1);
		
		sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	        	
	        	MyMenu myMenu = new MyMenu();
	    		myMenu.setId("1");
	    		myMenu.setName("欢迎使用");
	    		myMenu.setUrl("#");
	    		myMenu.setParent("-1");
	    		
	            while (rs.next()){
	    			MyMenu m = new MyMenu();
	    			
	    			m.setId(rs.getString("menu_id"));
	    			m.setName(rs.getString("menu_name"));
	    			m.setUrl(rs.getString("menu_url"));
	    			m.setParent(rs.getString("menu_parent"));
	    			
	    			//System.out.println(Json.toJson(m));
	    			myMenu.addChildMenu(m);
	    		}
	            System.out.println("callback end");
	            return myMenu;
	        }
	    });
		
		dao.execute(sql);
		System.out.println("execute end");
		
		MyMenu myMenu = sql.getObject(MyMenu.class);
		
		session.setAttribute("menus", myMenu);
	}
	
	@At
	@GET
	@Ok("jsp:jsp.test.addPerson")
	public void addPerson(HttpServletRequest req)
	{
		// 随机生成一个UserInfo
		UserInfo info = new UserInfo();
		info.setId(2);
		info.setName("test");
		info.setSex("男");
		info.setBirthday(new Date());
		info.setIdCard("1234567890987654321");
		info.setJoinTime(new Date());
		info.setGradeId(5);
		info.setCateId(1);
		info.setIcCard("422341241412423");
		info.setDepId(3);
		info.setJobNo("0007");
		
		req.setAttribute("defaul", info);
	}
	
	@At
	@POST
	public Object addPerson(@Param("..") UserInfo info,HttpSession session)
	{
		NutMap re = new NutMap();
		System.out.println(Json.toJson(info));
		
		return re.setv("ok",false);
	}
	
	@At
	public void fetchPerson()
	{
		UserInfo info = userInfoService.fetch(2);
		if(info != null)
		    System.out.println(Json.toJson(info));
		else
			System.out.println("null");

	}
	
	@At
	public void getAllPerson()
	{
		Sql sql = dao.sqls().create("testPerson.data");
		//Sql sql = Sqls.queryEntity("SELECT * FROM t_user,t_user_info where t_user.user_id = t_user_info.user_id");
		//Pager pager = dao.createPager(1, PageToolKit.getSize());
		Pager pager = dao.createPager(1, 1);

		pager.setRecordCount(2);// 记录数需手动设置
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql); 
		System.out.println(Json.toJson(sql.getList(Map.class)));
	}
	
	@At
	public void getAllPerson2()
	{
		Sql sql = dao.sqls().create("testPerson.data");
		sql.setPager(null);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql);
		
		Pager pager = dao.createPager(1, PageToolKit.getSize());
		pager.setRecordCount(sql.getList(Map.class).size());// 记录数需手动设置
		
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql); 
		System.out.println(Json.toJson(sql.getList(Map.class)));
	}
	
	@At
	public void getAllPerson3()
	{
        Sql sql = dao.sqls().create("getPersonSum.data");
		
		sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	        	int sums = 0;
	            while (rs.next()){
	    			sums = rs.getInt("sums");
	    		}
	            return sums;
	        }
	    });
		dao.execute(sql);
		int sum = sql.getInt();
		System.out.print(sum);
	}
	
	@At
	public void getAllPerson4()
	{
        int sum = getRecordByData("getPersonSum.data");
        System.out.print(sum);
        
        Sql sql = dao.sqls().create("testPerson.data");
		
		Pager pager = dao.createPager(1, PageToolKit.getSize());
		pager.setRecordCount(sum);// 记录数需手动设置
		
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql); 
		List<Map> rs = sql.getList(Map.class);
		System.out.println(Json.toJson(rs));
		
		for(int i = 0; i < rs.size(); i++)
		{
			Map m = rs.get(i);
			
			System.out.println(m.size());
			System.out.println(m.get("user_status"));
			
		}
	}
	
	
}
