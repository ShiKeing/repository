package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import po.Menus;
import po.ZhuCe;

public class ZhuCeDaoTest {
	private ZhuCeDao zhuceDao=new ZhuCeDao();

	@Test
	public void testAdd() {
		ZhuCe menu = new ZhuCe();
		menu.setName("111");
		menu.setPwd("1");
		menu.setRealname("1");
		menu.setSex("1");
		menu.setAge("1");
		menu.setCard("1");
		menu.setAddress("2");
		menu.setPhone("1");
		menu.setEmail("/img/aaaa");
		menu.setCode("1");
		menu.setType("/img/aaaa");
		
		System.out.println(ZhuCeDao.add(menu));
	}

}
