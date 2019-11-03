package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.Orders;
import po.OrdersSearch;
import po.Page;
import util.DBUtil;
import vo.OrdersInfo;
import vo.OrdersStatistics;

public class OrdersDao {
	//添加
	public int add(Orders order) {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "insert into orders(userid, "
				+ "menuid, menusum, times, delivery) "
				+ "values(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, order.getUserid());
			pstmt.setString(2, order.getMenuid());
			pstmt.setString(3, order.getMenusum());
			pstmt.setString(4, order.getTimes());
			pstmt.setString(5, order.getDelivery());
			//发送SQL
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
	//删除
	public int del(int id) {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "delete from orders where id=?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setInt(1, id);
			//发送SQL
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
	//修改
	public int chg(int id) {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "update orders set delivery=1 where id=?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setInt(1, id);
			//发送SQL
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
	//总条数
	public long count(OrdersSearch search) {
		Connection conn = DBUtil.getConn();
		String sql = "select "
				+ "count(*) "
				+ "from "
				+ "orders o, users u, menus m "
				+ "where "
				+ "o.userid=u.id and o.menuid=m.id ";
		ArrayList<String> params = new ArrayList<String>();
		long count = 0;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		//拼接SQL语句
		if(search != null) {
			if(search.getUserid() != null && search.getUserid().trim().length() != 0) {
				sql = sql + "and u.id=? ";
				params.add(search.getUserid());
			}
			if(search.getMenuname() != null && search.getMenuname().trim().length() != 0) {
				sql = sql + "and m.name=? ";
				params.add(search.getMenuname());
			}
			if(search.getDate() != null && search.getDate().trim().length() != 0) {
				sql = sql + "and o.times like ? ";
				params.add(search.getDate() + "%");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数？
			for(int index = 0; index < params.size(); index++) {
				pstmt.setObject(index+1, params.get(index));
			}
			//发送SQL
			rSet = pstmt.executeQuery();
			//处理结果
			if(rSet.next()) {
				count = rSet.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return count;
	}
	//搜索--分页查询
	public Page<OrdersInfo> findByPage(Page<OrdersInfo> page, OrdersSearch search) {
		Connection conn = DBUtil.getConn();
		String sql = "select "
				+ "o.id id, u.id userid, u.realname realname, "
				+ "u.phone phone, u.address address, m.name menuname, "
				+ "o.menusum menusum, m.price1 price1, o.menusum*m.price1 sum, "
				+ "o.times times, o.delivery delivery "
				+ "from "
				+ "orders o, users u, menus m "
				+ "where "
				+ "o.userid=u.id and o.menuid=m.id ";
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<OrdersInfo> list = new ArrayList<OrdersInfo>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		//拼接SQL语句
		if(search != null) {
			if(search.getUserid() != null && search.getUserid().trim().length() != 0) {
				sql = sql + "and u.id=? ";
				params.add(search.getUserid());
			}
			if(search.getMenuname() != null && search.getMenuname().trim().length() != 0) {
				sql = sql + "and m.name=? ";
				params.add(search.getMenuname());
			}
			if(search.getDate() != null && search.getDate().trim().length() != 0) {
				sql = sql + "and o.times like ? ";
				params.add(search.getDate() + "%");
			}
		}
		sql = sql + "limit ?, ?";
		params.add(page.getBeginIndex());
		params.add(page.getEveryPage());
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数？
			for(int index = 0; index < params.size(); index++) {
				pstmt.setObject(index+1, params.get(index));
			}
			//发送SQL
			rSet = pstmt.executeQuery();
			//处理结果
			while(rSet.next()) {
				OrdersInfo info = new OrdersInfo();
				info.setId(rSet.getInt(1));
				info.setUserid(rSet.getString(2));
				info.setRealname(rSet.getString(3));
				info.setPhone(rSet.getString(4));
				info.setAddress(rSet.getString(5));
				info.setMenuname(rSet.getString(6));
				info.setMenusum(rSet.getString(7));
				info.setPrice1(rSet.getString(8));
				info.setSum(rSet.getString(9));
				info.setTimes(rSet.getString(10));
				info.setDelivery(rSet.getString(11));
				list.add(info);
			}
			page.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return page;
	}
	//排行榜
	
	//统计
	public ArrayList<OrdersStatistics> findStatistics() {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "select "
				+ "m.id menuid, m.name menuname, "
				+ "sum(o.menusum) menusum, m.price1 price, "
				+ "sum(o.menusum)*m.price1 sum "
				+ "from "
				+ "orders o, menus m "
				+ "where "
				+ "o.menuid=m.id "
				+ "and "
				+ "o.times like ? "
				+ "group by m.id";
		//PreparedStatement
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<OrdersStatistics> list = new ArrayList<OrdersStatistics>();
		//获取年月日
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); //格式化
		String dateStr = sf.format(date);
		dateStr = dateStr + "%";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateStr);
			rSet = pstmt.executeQuery();
			while(rSet.next()) {
				OrdersStatistics statistics = new OrdersStatistics();
				statistics.setMenuid(rSet.getInt(1));
				statistics.setMenuname(rSet.getString(2));
				statistics.setMenusum(rSet.getString(3));
				statistics.setPrice(rSet.getString(4));
				statistics.setSum(rSet.getString(5));
				list.add(statistics);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
	}
}
