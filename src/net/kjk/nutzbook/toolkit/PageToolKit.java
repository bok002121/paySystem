package net.kjk.nutzbook.toolkit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public abstract class PageToolKit
{
	private static final int size = 10;

	public static int getSize()
	{
		return size;
	}
}
