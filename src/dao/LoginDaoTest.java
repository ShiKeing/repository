package dao;

import org.junit.Test;

public class LoginDaoTest {
	 private LoginDao loginDao=new LoginDao();
	@Test
	public void testFindByNameAndPwd() {
		System.out.println(loginDao.findByNameAndPwd("3","2"));
	}

}
