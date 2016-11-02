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
	//@Aop(TransAop.READ_COMMITTED)
	public int InsertFromExcel(String path,String[] titles)
	{
		// 判断头是否正常
		int r = checkExcelTitle(titles);
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
		
		Trans.exec(Connection.TRANSACTION_READ_COMMITTED,new Atom(){
		    public void run() {
		        
		    }
		});
		// 根据 r - 身份证信息，分开两批，一批是新用户，另一批是老用户
		
		// 新用户的话，就先插入 t_user 在，再插入 t_user_info，因为不多，就不要考虑那么多啦。 
		
		
		return 0;
	}
	
	private List<Map> getExcelContent()
	{
		List<Map> content = new ArrayList<Map>();
		
		return content;
	}
	
	/*
	 * 判断是否存在必须项目
	 */
	private Boolean checkExcelTitle(String path)
	{
		String[] needStr = {"姓名","性别","出生年月","身份证号码","参加工作时间","职别","类别","银行卡账号","部门","工号"};
		String[] titles = ReadExcel.readExcelTitle(path);
		Boolean flag = true;
		
		for(int i = 0; i < needStr.length; i++)
		{
			if(needStr[i].equals(titles[i]))
			{
				continue;
			}
			else
			{
				flag = false;
				break;
			}
				
		}
		return flag;
	}
}
