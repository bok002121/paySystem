package net.kjk.nutzbook.service;

import net.kjk.nutzbook.bean.UserInfo;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

@IocBean(fields="dao")
public class UserInfoService extends IdNameEntityService<UserInfo>
{
	public Boolean addPerson(UserInfo info)
	{
		dao().insert(info);
		return true;
	}
}
