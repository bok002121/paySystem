package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.kjk.nutzbook.toolkit.ReadExcel;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@IocBean
public class PersonService extends BaseService
{
	@Inject protected UserService userService;
	@Inject protected UserInfoService userInfoService;
	@Inject protected PropertiesProxy excelConf;
	/*
	 * -1 表示标题头不对
	 */
	@Aop(TransAop.READ_COMMITTED)
	public int InsertFromExcel(String path)
	{
		// 判断头是否正常
		int r = checkExcelTitle(path);
		if( r == 0)
		{
			return -1;
		}
		
		// 获取职别
		Map<String,String> gradeMap = getGradeMap();
		// 获取类别
		Map<String,String> cateMap = getCategoryMap();			
		// 获取部门
		Map<String,String> depMap = getDepartmentMap();
		
		// 正常的话，读取excel表
		/*
		 * 0 - 姓名      1 - 性别   2 - 出生年月
		 * 3 - 身份证  4 - 参加工作时间  5 - 职别
		 * 6 - 类别  7 - 银行卡账号  8 - 部门
		 * 9 - 工号
		 */
		Map<String,String> field = excelConf.toMap();
		List<List<String>> contents = ReadExcel.readExcelContentToListList(path,field,
				                                                   gradeMap,cateMap,depMap);
		System.out.println(Json.toJson(contents));
		List<Map> tm = null;
		List<List<String>> newContents = new ArrayList<List<String>>();
		List<List<String>> oldContents = new ArrayList<List<String>>();
		
		for(int i = 0; i< contents.size(); i++ )
        {	
			// 判断存在与否
			String id_card = contents.get(i).get(excelConf.getInt("id_card"));
        	// 先找是否存在
        	tm = fetchData("select user_id from t_user_info where id_card = '" + id_card + "'");
        	
        	if( tm.size() == 0)
        	{
        		// 空的话，先在 t_user 插入
        		int id = userService.add(id_card);
        		contents.get(i).add(String.valueOf(id));
        		newContents.add(contents.get(i));
        	}
        	else
        	{
        		String id = (String)(tm.get(0).get("user_id"));
        		contents.get(i).add(id);
        		oldContents.add(contents.get(i));
        	}
        }
		// 遍历新用户集合
		userInfoService.insertListList(newContents, field);
		
		// 遍历老用户集合
		userInfoService.updateListList(oldContents, field);
		
		System.out.println(Json.toJson(newContents));
		
		System.out.println(Json.toJson(oldContents));
		
		newContents.clear();
		oldContents.clear();
		contents.clear();
		
		return contents.size();
	}
	
	/*
	 * 判断是否存在必须项目
	 */
	private int checkExcelTitle(String path)
	{
		String[] needStr = {"姓名","性别","出生年月","身份证","参加工作时间","职别","类别","银行卡账号","部门","工号"};
		String[] titles = ReadExcel.readExcelTitle(path);
		int flag = 1;
		
		for(int i = 0; i < needStr.length; i++)
		{
			if(needStr[i].equals(titles[i]))
			{
				continue;
			}
			else
			{
				flag = 0;
				break;
			}
				
		}
		return flag;
	}
	
	/*
	 * 获取 grades Map name - id
	 */
	private Map<String,String> getGradeMap()
	{
		Map<String,String> grade = new HashMap<String,String>();
		
		Sql sql = dao.sqls().create("selectAllGrade.data");

   		sql.setCallback(new SqlCallback()
   		{
   			public Object invoke(Connection conn, ResultSet rs, Sql sql)
   					throws SQLException
   			{
   				Map<String,String> grade = new HashMap<String,String>();
   				while (rs.next())
   				{
   					String name = rs.getString("grade_name");
   					String id = rs.getString("grade_id");
   					grade.put(name, id);
   				}
   				return grade;
   			}
   		});
   		dao.execute(sql);
   		
   		grade = sql.getObject(Map.class);
   		
   		//System.out.println(Json.toJson(grade));
		
		return grade;
	}
	
	/*
	 * 获取 category Map name - id
	 */
	private Map<String,String> getCategoryMap()
	{
		Map<String,String> cate = new HashMap<String,String>();
		
		Sql sql = dao.sqls().create("selectAllCate.data");

   		sql.setCallback(new SqlCallback()
   		{
   			public Object invoke(Connection conn, ResultSet rs, Sql sql)
   					throws SQLException
   			{
   				Map<String,String> cate = new HashMap<String,String>();
   				while (rs.next())
   				{
   					String name = rs.getString("cate_name");
   					String id = rs.getString("cate_id");
   					cate.put(name, id);
   				}
   				return cate;
   			}
   		});
   		dao.execute(sql);
   		
   		cate = sql.getObject(Map.class);
   		
   		//System.out.println(Json.toJson(cate));
		
		return cate;
	}
	
	/*
	 * 获取 t_department Map name - id
	 */
	private Map<String,String> getDepartmentMap()
	{
		Map<String,String> dep = new HashMap<String,String>();
		
		Sql sql = dao.sqls().create("selectAllDep.data");

   		sql.setCallback(new SqlCallback()
   		{
   			public Object invoke(Connection conn, ResultSet rs, Sql sql)
   					throws SQLException
   			{
   				Map<String,String> dep = new HashMap<String,String>();
   				while (rs.next())
   				{
   					String name = rs.getString("dep_name");
   					String id = rs.getString("dep_id");
   					dep.put(name, id);
   				}
   				return dep;
   			}
   		});
   		dao.execute(sql);
   		
   		dep = sql.getObject(Map.class);
   		
   		//System.out.println(Json.toJson(dep));
		
		return dep;
	}
	
	
}
