package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import po.Menus;
import po.Page;
import util.PageUtil;
import vo.MenusInfo;

public class MenusDaoTest {
	private MenusDao dao = new MenusDao();
	@Test
	public void testFindAll() {
		ArrayList<MenusInfo> list = dao.findAll();
		
		for (MenusInfo menusInfo : list) {
			System.out.println(menusInfo);
		}
	}
	
	@Test
	public void testCount() {
		long count = dao.count();
		System.out.println(count);
	}

	@Test
	public void testFindByPage() {
		long totalCount = dao.count();
		Page<MenusInfo> page = PageUtil.createPage(2, (int)totalCount, 4);
		page = dao.findByPage(page);
		
		List<MenusInfo> list = page.getList();
		for (MenusInfo menusInfo : list) {
			System.out.println(menusInfo);
		}
	}
	
	@Test
	public void testAdd() {
		Menus menu = new Menus();
		menu.setName("111");
		menu.setTypeid("1");
		menu.setBurden("1");
		menu.setBrief("1");
		menu.setPrice("1");
		menu.setSums("1");
		menu.setSums1("2");
		menu.setPrice1("1");
		menu.setImgpath("/img/aaaa");
		System.out.println(dao.add(menu));
	}
	
	@Test
	public void testDel() {
		System.out.println(dao.del(21));
	}
	
	@Test
	public void testChg() {
		Menus menu = new Menus();
		menu.setName("111");
		menu.setTypeid("1");
		menu.setBurden("1");
		menu.setBrief("1");
		menu.setPrice("1");
		menu.setSums("1");
		menu.setSums1("2");
		menu.setPrice1("1");
		menu.setImgpath("/img/aaaa");
		
		System.out.println(dao.chg(19, menu));
	}
	
	@Test
	public void testFindById() {
		System.out.println(dao.findById(199));
	}
	
	@Test
	public void testFindByName() {
		System.out.println(dao.findByName("粉蒸肉111"));
	}
	
	
	
}


















