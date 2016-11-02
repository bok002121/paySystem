package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kjk.nutzbook.bean.Category;
import net.kjk.nutzbook.bean.Department;
import net.kjk.nutzbook.bean.Grade;
import net.kjk.nutzbook.toolkit.PageToolKit;
import net.kjk.nutzbook.toolkit.ReadExcel;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

@IocBean
public class PersonService extends BaseService
{
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
		List<Grade> grades = dao.query(Grade.class,null);
		// 获取类别
		List<Category> cates = dao.query(Category.class,null);				
		// 获取部门
		List<Department> deps = dao.query(Department.class,null);
		
		// 正常的话，读取excel表
		/*
		 * 0 - 姓名      1 - 性别   2 - 出生年月
		 * 3 - 身份证  4 - 参加工作时间  5 - 职别
		 * 6 - 类别  7 - 银行卡账号  8 - 部门
		 * 9 - 工号
		 */
		List<String> contents = ReadExcel.readExcelContentToList(path);
		List<Map> tm = null;
		for(int i = 0; i< contents.size(); i++ )
        {
        	String[] t =  new String[10];
        	t = contents.get(i).split(",");
        	
        	// 先找是否存在
        	tm = fetchData("select user_id from t_user_info where ic_card = '" + t[3] + "'");
        	
        	if( tm == null)
        	{
        		// 空的话，先在 t_user 插入
        	}
        	else
        	{
        		
        	}
        	
        	
        }
		// 根据 r - 身份证信息，分开两批，一批是新用户，另一批是老用户
		
		// 新用户的话，就先插入 t_user 在，再插入 t_user_info，因为不多，就不要考虑那么多啦。 
		
		
		return 0;
	}
	
	/*
	 * 判断是否存在必须项目
	 */
	private int checkExcelTitle(String path)
	{
		String[] needStr = {"姓名","性别","出生年月","身份证号码","参加工作时间","职别","类别","银行卡账号","部门","工号"};
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
}
