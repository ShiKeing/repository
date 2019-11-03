package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import po.ZhuCe;
import util.DBUtil;

public class ZhuCeDao {
	public static int add( ZhuCe zhuce)
	{   // 创建连接
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "insert into users(id,name,pwd,realname,sex,age,card,address,phone,email,code,type) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?);";
		// 创建preparedStatem
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1,zhuce.getId());
			pstmt.setString(2,zhuce.getName());
			pstmt.setString(3,zhuce.getPwd());
			pstmt.setString(4,zhuce.getRealname());
			pstmt.setString(5,zhuce.getSex());
			pstmt.setString(6,zhuce.getAge());
			pstmt.setString(7,zhuce.getCard());
			pstmt.setString(8,zhuce.getAddress());
			pstmt.setString(9,zhuce.getPhone());
			pstmt.setString(10,zhuce.getEmail());
			pstmt.setString(11,zhuce.getCode());
			pstmt.setString(12,zhuce.getType());
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
	public static ZhuCe findByName(String name)
	{
		Connection conn = DBUtil.getConn();
		// SQL语句
		String sql = "select * from users where name=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
	     ZhuCe zhuce = null;
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			rSet = pstmt.executeQuery();
			if(rSet.next())
			{
				zhuce= new ZhuCe();
				zhuce.setId(rSet.getInt(1));
				zhuce.setName(rSet.getString(2));
				zhuce.setPwd(rSet.getString(3));
				zhuce.setRealname(rSet.getString(4));
				zhuce.setSex(rSet.getString(5));
				zhuce.setAge(rSet.getString(6));
				zhuce.setCard(rSet.getString(7));
				zhuce.setAddress(rSet.getString(8));
				zhuce.setPhone(rSet.getString(9));
				zhuce.setEmail(rSet.getString(10));
				zhuce.setCode(rSet.getString(11));
				zhuce.setType(rSet.getString(12));
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
		return zhuce;
	}
	
}