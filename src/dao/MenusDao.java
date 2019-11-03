package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import po.Menus;
import po.Page;
import po.Types;
import util.DBUtil;
import vo.MenusInfo;

public class MenusDao{
	//查询所有-----分页查询
	public long count()
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select "
				+ "count(*) "
				+ "from "
				+ "menus m, types t "
				+ "where "
				+ "m.typeid=t.id ";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		long count=0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 发送SQL语句
			rSet = pstmt.executeQuery();
			if (rSet.next())
			{
				count=rSet.getInt(1);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return count;
	}
	public ArrayList<MenusInfo> findAll()
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select  m.id id, m.name name, t.id typeid, "
				+ " t.name typename, m.burden burden, m.brief brief, "
				+ "m.price price, m.sums sums, m.price1 price1,"
				+ " m.sums1 sums1, m.imgpath imgpath "
				+ "from menus m, types t "
				+ "where  "
				+ "m.typeid=t.id";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<MenusInfo> list = new ArrayList<MenusInfo>();
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 发送SQL语句
			rSet = pstmt.executeQuery();
			while (rSet.next())
			{
				MenusInfo menusInfo= new MenusInfo();
				menusInfo.setId(rSet.getInt(1));
				menusInfo.setName(rSet.getString(2));
				menusInfo.setTypeid(rSet.getString(3));
				menusInfo.setTypename(rSet.getString(4));
				menusInfo.setBurden(rSet.getString(5));
				menusInfo.setBrief(rSet.getString(6));
				menusInfo.setPrice(rSet.getString(7));
				menusInfo.setSums(rSet.getString(8));
				menusInfo.setPrice1(rSet.getString(9));
				menusInfo.setSums1(rSet.getString(10));
				menusInfo.setImgpath(rSet.getString(11));
				list.add(menusInfo);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	public Page<MenusInfo> findByPage(Page<MenusInfo> page) {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "select  m.id id, m.name name, t.id typeid, "
				+ " t.name typename, m.burden burden, m.brief brief, "
				+ "m.price price, m.sums sums, m.price1 price1, "
				+ " m.sums1 sums1, m.imgpath imgpath "
				+ "from menus m, types t "
				+ "where  "
				+ "m.typeid=t.id limit ?, ?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<MenusInfo> list = new ArrayList<MenusInfo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			//设置分页参数
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rSet = pstmt.executeQuery();
			
			while(rSet.next()) {
				MenusInfo menusInfo = new MenusInfo();
				menusInfo.setId(rSet.getInt(1));
				menusInfo.setName(rSet.getString(2));
				menusInfo.setTypeid(rSet.getString(3));
				menusInfo.setTypename(rSet.getString(4));
				menusInfo.setBurden(rSet.getString(5));
				menusInfo.setBrief(rSet.getString(6));
				menusInfo.setPrice(rSet.getString(7));
				menusInfo.setSums(rSet.getString(8));
				menusInfo.setPrice1(rSet.getString(9));
				menusInfo.setSums1(rSet.getString(10));
				menusInfo.setImgpath(rSet.getString(11));
				
				list.add(menusInfo);
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
	public int add( Menus menus)
	{   // 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "insert into menus(name,typeid,burden,brief,price,sums,price1,sums1,imgpath) "
				+ "values(?,?,?,?,?,?,?,?,?);";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1,menus.getName());
			pstmt.setString(2,menus.getTypeid());
			pstmt.setString(3,menus.getBurden());
			pstmt.setString(4,menus.getBrief());
			pstmt.setString(5,menus.getPrice());
			pstmt.setString(6,menus.getSums());
			pstmt.setString(7,menus.getPrice1());
			pstmt.setString(8,menus.getSums1());
			pstmt.setString(9,menus.getImgpath());
			// 发送SQL语句
			result = pstmt.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
	public int del(int id)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "delete from menus where id = ?;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, id);
			// 发送SQL语句
			result = pstmt.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
	public int chg(int id, Menus menu) {
		//获取连接
		Connection conn = DBUtil.getConn();
		//SQL语句
		String sql = "update menus set "
				+ "name=?, burden=?, price=?, price1=?, brief=?, typeid=? "
				+ "where id=?";
		//PreparedStatement
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, menu.getName());
			pstmt.setString(2, menu.getBurden());
			pstmt.setString(3, menu.getPrice());
			pstmt.setString(4, menu.getPrice1());
			pstmt.setString(5, menu.getBrief());
			pstmt.setString(6, menu.getTypeid());
			pstmt.setInt(7, id);
			//发送SQL语句
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		//返回
		return result;
	}
	public Menus findById(int id)
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from menus where id=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Menus menus = null;
	try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rSet = pstmt.executeQuery();
			if(rSet.next())
			{
				menus= new Menus();
				menus.setId(rSet.getInt(1));
				menus.setName(rSet.getString(2));
				menus.setTypeid(rSet.getString(3));
				menus.setBurden(rSet.getString(4));
				menus.setBrief(rSet.getString(5));
				menus.setPrice(rSet.getString(6));
				menus.setSums(rSet.getString(7));
				menus.setPrice1(rSet.getString(8));
				menus.setSums1(rSet.getString(9));
				menus.setImgpath(rSet.getString(10));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return menus;
	}
	public Menus findByName(String name)
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from menus where name=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Menus menus = null;
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			rSet = pstmt.executeQuery();
			if(rSet.next())
			{
				menus= new Menus();
				menus.setId(rSet.getInt(1));
				menus.setName(rSet.getString(2));
				menus.setTypeid(rSet.getString(3));
				menus.setBurden(rSet.getString(4));
				menus.setBrief(rSet.getString(5));
				menus.setPrice(rSet.getString(6));
				menus.setSums(rSet.getString(7));
				menus.setPrice1(rSet.getString(8));
				menus.setSums1(rSet.getString(9));
				menus.setImgpath(rSet.getString(10));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return menus;
	}
	
}

