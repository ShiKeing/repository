package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.Admin;
import po.User;
import service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//登录
		User user = userService.login(name, pwd);
		if(user != null) {//成功
			//将用户信息存放在session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//转发
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {//失败---->/admin/index.jsp
			PrintWriter out = response.getWriter();
			out.print("<script>"
					+ "alert('用户名或密码错误');"
					+ "window.location.href='"
					+ request.getContextPath()
					+ "/qiantai/login.jsp';"
					+ "</script>");		
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("login"))
		{
			login(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
