package net.kjk.nutzbook.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;


public abstract class BaseService
{
	/** 注入同名的一个ioc对象 */
    @Inject protected Dao dao;
    
    
    /*
     * 查询 一个的
     * 发现传入 .data  或者传入sql语句都可以
     */
    protected List<Map> fetchData(String sqlStr)
   	{
    	//System.out.println(sqlStr);
   		Sql sql = Sqls.create(sqlStr);
   		
   		System.out.println(sqlStr);

   		sql.setPager(null);
   		sql.setCallback(Sqls.callback.records());
   		
   		dao.execute(sql);
   		
   		
   		return sql.getList(Map.class);
   	}
    
    /*
   	 * sql : 传入 .sql 中的键值
   	 * 要求 查询结果只剩下一列 一行。且是总数。
   	 */
   	protected int getRecordByData(String sqlStr)
   	{
   		Sql sql = dao.sqls().create(sqlStr);

   		sql.setCallback(new SqlCallback()
   		{
   			public Object invoke(Connection conn, ResultSet rs, Sql sql)
   					throws SQLException
   			{
   				int sums = 0;
   				while (rs.next())
   				{
   					sums = rs.getInt(1);
   				}
   				return sums;
   			}
   		});
   		dao.execute(sql);
   		
   		return sql.getInt();
   	}
   	
   	/*
   	 * sql : 传入 sql 语句
   	 * 要求 查询结果只剩下一列 一行。且是总数。
   	 */
   	protected int getRecordByStatement(String sqlStr)
   	{
   		Sql sql = Sqls.create(sqlStr);

   		sql.setCallback(new SqlCallback()
   		{
   			public Object invoke(Connection conn, ResultSet rs, Sql sql)
   					throws SQLException
   			{
   				int sums = 0;
   				while (rs.next())
   				{
   					sums = rs.getInt(1);
   				}
   				return sums;
   			}
   		});
   		dao.execute(sql);
   		return sql.getInt();
   	}
}
