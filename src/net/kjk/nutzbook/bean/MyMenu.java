package net.kjk.nutzbook.bean;

import java.util.HashMap;
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
	private Map<String,MyMenu> child = new HashMap<String,MyMenu>();  
	
	public MyMenu()
	{
		this.parent = "1";
	}
	
	/*
	 * 添加一个子菜单
	 */
	public void addChildMenu(MyMenu m)
	{
		String key = m.getId();
		
		// 有点地柜的味道
		if(isChild(m))
		{
			// 所属菜单 直接插入
			child.put(key, m);
		}
		else
		{
			// 如果不存在的话，则先判断是否为空
			if(child.isEmpty())
			{
				// 如果是空的话，说明不属于你这里
				return ;
			}
			else
			{
				String parent = m.getParent();
				if(child.containsKey(parent))
				{
					child.get(parent).addChildMenu(m);
				}
				else
				{
					System.out.println("other");
				}
				//child.get(key).addChildMenu(m);
			}
		}
	}
	
	private Boolean isChild(MyMenu m)
	{
		if( this.id.equals(m.getParent()))
		{
			return true;
		}
		else
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


	public Map<String, MyMenu> getChild()
	{
		return child;
	}

	public void setChild(Map<String, MyMenu> child)
	{
		this.child = child;
	}
	
	
}
