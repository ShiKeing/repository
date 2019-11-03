package service;

import dao.LoginDao;
import po.User;

public class UserService {
	public LoginDao loginDao=new LoginDao();
	  public User login(String name,String pwd)
	  {
		  return loginDao.findByNameAndPwd(name, pwd);
	  }
	  
}
