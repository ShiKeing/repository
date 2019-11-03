package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.Admin;
import util.DBUtil;

public class AdminDao {
	public Admin findByNameAndPwd(String name,String pwd)
	{
		Connection conn=DBUtil.getConn();
		String sql="select *from admin where name=? and pwd=? ";
		//这句话必须要有
		PreparedStatement pstmt=null;
		Admin admin = null;
		ResultSet rSet=null;
		try {
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			//发送SQL
			rSet=pstmt.executeQuery();
			//处理结果 rSet--->User
			if(rSet.next())
			{
				admin= new Admin();
				admin.setId(rSet.getInt(1));
				admin.setName(rSet.getString(2));
				admin.setPwd(rSet.getString(3));
				admin.setAuthrity(rSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		  }
		 return admin;
	   }
	public Admin findByName(String name)
	{
		// 创建连接
				Connection conn = DBUtil.getConn();
				// SQL语句
				String sql = "select * from admin where name = ?;";
				// 创建preparedStatem
				PreparedStatement pstmt = null;
				ResultSet rSet = null;
				Admin result = null;
				try
				{
					pstmt = conn.prepareStatement(sql);
					// 设置参数
					pstmt.setString(1, name);
					// 发送SQL语句
					rSet = pstmt.executeQuery();
					if (rSet.next())
					{
						result = new Admin();
						result.setId(rSet.getInt(1));
						result.setName(rSet.getString(2));
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
				return result;
			}
	public int chg(int id, Admin admin)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "update admin set name= ?,pwd=? where id = ?;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, admin.getName());
			pstmt.setString(2,admin.getPwd());
			pstmt.setInt(3,id);

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

	 
