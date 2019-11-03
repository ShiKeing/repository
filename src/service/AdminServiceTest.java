package service;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.AdminDao;
import po.Admin;

public class AdminServiceTest {
   private AdminService adminService =new AdminService();
	@Test
	public void testLogin() {
		
	}

	@Test
	public void testChg() {
		Admin admin=new Admin();
		admin.setName("admin");
		admin.setPwd("121");
		System.out.println(adminService.chg(1, admin));
	}

}
