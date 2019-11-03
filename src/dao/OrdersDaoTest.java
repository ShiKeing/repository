package dao;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import po.OrdersSearch;
import po.Page;
import util.PageUtil;
import vo.OrdersInfo;
import vo.OrdersStatistics;

public class OrdersDaoTest {
	private OrdersDao ordersDao = new OrdersDao();
	/*
	@Test
	public void testFindStatistics() {
		ArrayList<OrdersStatistics> list = ordersDao.findStatistics();
		for (OrdersStatistics ordersStatistics : list) {
			System.out.println(ordersStatistics);
		}
	}
	@Test
	public void testCount() {
		OrdersSearch search = new OrdersSearch();
		search.setUserid("5");
		search.setMenuname("黄瓜拉皮");
		search.setDate("2017-09-14");
		long count = ordersDao.count(search);
		System.out.println(count);
	}*/
	
	@Test
	public void testFindByPage() {
		OrdersSearch search = new OrdersSearch();
		//search.setUserid("5");
		//search.setMenuname("黄瓜拉皮");
		search.setDate("2017-09-14");
		long count = ordersDao.count(search);
		
		Page<OrdersInfo> page = PageUtil.createPage(2, (int)count, 2);
		page = ordersDao.findByPage(page, search);
		
		List<OrdersInfo> list = page.getList();
		for (OrdersInfo ordersInfo : list) {
			System.out.println(ordersInfo);
		}
	}
}
