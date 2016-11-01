package net.kjk.nutzbook.service;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean

public class PersonService extends BaseService
{
	/*
	 * -1 表示标题头不对
	 */
	@Aop(TransAop.READ_COMMITTED)
	public int InsertFromExcel(String path,String[] titles)
	{
		// 判断头是否正常
		int r = checkExcelTitle(titles);
		if( r == 0)
		{
			return -1;
		}
		
		// 正常的话，读取excel表
		
		
		// 根据 r - 身份证信息，分开两批，一批是新用户，另一批是老用户
		
		// 新用户的话，就先插入 t_user 在，再插入 t_user_info，因为不多，就不要考虑那么多啦。 
		
		
		return 0;
	}
	
	/*
	 * 判断是否存在必须项目
	 */
	private int checkExcelTitle(String[] titles)
	{
		String[] needStr = {"姓名","身份证"};
		Boolean flag = true;
		int r = 0;
		
		for(int i = 0; i < needStr.length; i++)
		{
			flag = false;
			
			for(int k = 0; k < titles.length; k++)
			{
				if(needStr[i].equals(titles[k]))
				{
					flag = true;
					if( i == 1)
						r = k;
					break;
				}
			}
			
			if( flag == false)
			{
				break;
			}
		}
		
		return r;
	}
}
