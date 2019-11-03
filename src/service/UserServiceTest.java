package service;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.LoginDao;

public class UserServiceTest {
	private UserService userService=new UserService();

	@Test
	public void testLogin() {
		System.out.println(userService.login("3","2"));
	}

}
