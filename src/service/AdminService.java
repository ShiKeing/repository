package service;

import dao.AdminDao;
import po.Admin;

public class AdminService {
	public AdminDao adminDao=new AdminDao();
  public Admin login(String name,String pwd)
  {
	  return adminDao.findByNameAndPwd(name, pwd);
  }
  public int chg(int id,Admin admin)
  {
	  Admin a=adminDao.findByName(admin.getName());
	  if(a !=null && a.getId() !=id)
	  {
		  return -1;
	  }
	  return adminDao.chg(id, admin);
  }
}
