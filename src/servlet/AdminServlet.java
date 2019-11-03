package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.Admin;
import service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//登录
		Admin admin = adminService.login(name, pwd);
		if(admin != null) {//成功---->/admin/main.jsp
			//将用户信息存放在session中
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			//转发
			request.getRequestDispatcher("/admin/main.jsp").forward(request, response);
		} else {//失败---->/admin/index.jsp
			PrintWriter out = response.getWriter();
			out.print("<script>"
					+ "alert('用户名或密码错误');"
					+ "window.location.href='"
					+ request.getContextPath()
					+ "/admin/index.jsp';"
					+ "</script>");		
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从Session中删除用户信息
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		//跳转到登录页面
		PrintWriter out = response.getWriter();
		out.print("<script>"
				+ "window.parent.location.href='"
				+ request.getContextPath()
				+ "/admin/index.jsp';"
				+ "</script>");
	}
	
	protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPwd(pwd);
		//修改
		int result = adminService.chg(id, admin);
		PrintWriter out = response.getWriter();
		if(result == 1) {
			HttpSession session = request.getSession();
			session.removeAttribute("admin");
    		out.print("<script>"
    				+ "alert('修改成功，请重新登录!');"
    				+ "window.parent.location.href='" 
    				+ request.getContextPath() 
    				+ "/admin/index.jsp';"
    				+ "</script>");
		} else {
    		out.print("<script>"
    				+ "alert('修改失败!');"
    				+ "window.location.href='" 
    				+ request.getContextPath() 
    				+ "/admin/admin_update.jsp';"
    				+ "</script>");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("login")) {
			login(request, response);
		} else if(method.equals("logout")) {
			logout(request, response);
		} else if(method.equals("chg")) {
			chg(request, response);
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
