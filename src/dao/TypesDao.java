package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.Types;
import util.DBUtil;

public class TypesDao
{
	// 添加
	public int add(Types type)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "insert into types (name) values(?);";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;

		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, type.getName());
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

	// 删除
	public int del(int id)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "delete from types where id = ?;";
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

	// 修改
	public int chg(int id, String name)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "update types set name = ? where id = ?;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;

		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
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
	
	// 查询所有
	public ArrayList<Types> findAll()
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from types;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<Types> result = new ArrayList<Types>();

		try
		{
			pstmt = conn.prepareStatement(sql);
			// 发送SQL语句
			rSet = pstmt.executeQuery();
			while (rSet.next())
			{
				Types types = new Types();
				types.setId(rSet.getInt(1));
				types.setName(rSet.getString(2));
				result.add(types);
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
	// 查询单个通过Id
	public Types findById(int id)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from types where id = ?;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Types result = null;

		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, id);
			// 发送SQL语句
			rSet = pstmt.executeQuery();
			if (rSet.next())
			{
				result = new Types();
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

	// 查询单个通过name
	public Types findByName(String name)
	{
		// 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from types where name = ?;";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Types result = null;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, name);
			// 发送SQL语句
			rSet = pstmt.executeQuery();
			if (rSet.next())
			{
				result = new Types();
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
}
