package net.kjk.nutzbook.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 建立之后 要设置好所有属性
 * 
 */
public class MyMenu
{
	private String id;
	private String name;
	private String url;
	private String parent;
	// String,父节点，MyMenu子节点
	// private Map<String,MyMenu> child = new HashMap<String,MyMenu>();
	private List<MyMenu> child = new ArrayList<MyMenu>();

	public MyMenu()
	{
		this.parent = "1";
	}

	/*
	 * 添加一个子菜单
	 */
	public int addChildMenu(MyMenu m)
	{
		// 有点地柜的味道
		if (isChild(m))
		{
			// 所属菜单 直接插入
			child.add(m);
			return 1;
		} 
		else
		{
			// 沒有的話，則遍歷子菜單欄進行插入，成功返回-1，則能稍微提前點結束
			for (int i = 0; i < child.size(); i++)
			{
				if (child.get(i).addChildMenu(m) == 1)
				{
					return 1;
				}
			}
		}
		return -1;
	}

	private Boolean isChild(MyMenu m)
	{
		if (this.id.equals(m.getParent()))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getParent()
	{
		return parent;
	}

	public void setParent(String parent)
	{
		this.parent = parent;
	}

	// public Map<String, MyMenu> getChild()
	// {
	// return child;
	// }
	//
	// public void setChild(Map<String, MyMenu> child)
	// {
	// this.child = child;
	// }

	public List<MyMenu> getChild()
	{
		return child;
	}

	public void setChilds(List<MyMenu> child)
	{
		this.child = child;
	}

}
