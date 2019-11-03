package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.User;
import util.DBUtil;

public class LoginDao {
	public User findByNameAndPwd(String name,String pwd)
	{
		Connection conn=DBUtil.getConn();
		String sql="select id,name,pwd,realname from users where name=? and pwd=? ";
		//这句话必须要有
		PreparedStatement pstmt=null;
		User user = null;
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
				user= new User();
				user.setId(rSet.getInt(1));
				user.setName(rSet.getString(2));
				user.setPwd(rSet.getString(3));
				user.setRealname(rSet.getString(4));
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
		 return user;
	   }
}
