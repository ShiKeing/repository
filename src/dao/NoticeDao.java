package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.Menus;
import po.Notice;
import po.Orders;
import util.DBUtil;

public class NoticeDao {
	public ArrayList<Notice> findAll()
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		 String sql = "select *from notice;";
		 PreparedStatement pstmt = null;
		 ResultSet rSet = null;
		 ArrayList<Notice> list = new ArrayList<Notice>();
			try
			{   
				pstmt = conn.prepareStatement(sql);
				// 发送SQL语句
				rSet = pstmt.executeQuery();
				while (rSet.next())
				{
					Notice notice=new Notice();
					notice.setId(rSet.getInt(1));
					notice.setName(rSet.getString(2));
					notice.setContent(rSet.getString(3));
					notice.setTimes(rSet.getString(4));
					list.add(notice);
				}
			} catch (SQLException e)
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
	public int del(int id)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "delete from notice where id = ?;";
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
	public int add( Notice notice)
	{   // 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "insert into notice(name,content,times) "
				+ "values(?,?,?)";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			
			pstmt.setString(1,notice.getName());
			pstmt.setString(2,notice.getContent());
			pstmt.setString(3,notice.getTimes());
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
}
