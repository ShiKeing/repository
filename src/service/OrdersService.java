package service;

import java.util.ArrayList;

import dao.OrdersDao;
import po.OrdersSearch;
import po.Page;
import vo.OrdersInfo;
import vo.OrdersStatistics;

public class OrdersService {
	private OrdersDao ordersDao = new OrdersDao();
	public ArrayList<OrdersStatistics> findStatistics() {
		return ordersDao.findStatistics();
	}
	
	public long count(OrdersSearch search) {
		return ordersDao.count(search);
	}
	
	public Page<OrdersInfo> findByPage(Page<OrdersInfo> page, OrdersSearch search) {
		return ordersDao.findByPage(page, search);
	}
	
	public int chg(int id) {
		return ordersDao.chg(id);
	}
	
	public int del(int id) {
		return ordersDao.del(id);
	}
	
	
	
}
