package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mysql.jdbc.DatabaseMetaDataUsingInfoSchema;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import po.Admin;

public class AdminDaoTest {
	 private AdminDao adminDao=new AdminDao();
	@Test
	public void testFindByNameAndPwd() {
				System.out.println(adminDao.findByNameAndPwd("1","sa"));
	}

	@Test
	public void testFindByName() {
		System.out.println(adminDao.findByName("sa"));
	}

	@Test
	public void testChg() {
		Admin admin=new Admin();
		admin.setName("sa");
		admin.setPwd("1");
		System.out.print(adminDao.chg(2,admin));
	}

}
