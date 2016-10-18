package net.kjk.nutzbook.bean;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_group")
public class Role
{
	@Id
	@Column("group_id")
	private int id;
	
	@Name
	@Column("group_name")
	private String name;
	
	@ManyMany(target=Menu.class,relation="t_group_menu",
			from="group_id",to="menu_id")
	List<Menu> menus;
}
