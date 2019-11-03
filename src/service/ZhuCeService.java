package service;

import dao.ZhuCeDao;
import po.Menus;
import po.ZhuCe;

public class ZhuCeService {
	private ZhuCeService zhuCeService=new ZhuCeService();
	public ZhuCe findByName (String name)
	{
	  return ZhuCeDao.findByName(name);	
	}
	public static int add(ZhuCe zhuce)
	{
		return ZhuCeDao.add(zhuce);
	}
	
	
}
