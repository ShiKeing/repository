package service;
import dao.MenusDao;
import po.Menus;
import po.Page;
import vo.MenusInfo;

public class MenusService {
      //查询一共有多少条
	private MenusDao menusDao=new MenusDao();
	public long count()
	{
		return menusDao.count();
	}
	public Page<MenusInfo> findByPage(Page<MenusInfo>page)
	{
	   return menusDao.findByPage(page);
	}
	public int add(Menus menus)
	{
		//根据菜品名称查询
		Menus m=menusDao.findByName(menus.getName());
		if(m !=null)
		{
			return -1;
		}
		return menusDao.add(menus);
	}
	 public int del(int id)
	{
		return menusDao.del(id);
	}
	 public int chg(Integer id,Menus menus)
	  {
		Menus m=menusDao.findByName(menus.getName());
		if(m != null&&m.getId()!=id)
		{
			return -1;
		}
		return menusDao.chg(id, menus);
	   }
	public Menus findById (int id)
	{
	  return menusDao.findById(id);	
	}
	
	
}
	
	

