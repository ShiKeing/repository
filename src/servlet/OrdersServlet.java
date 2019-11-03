package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.OrdersSearch;
import po.Page;
import service.OrdersService;
import util.PageUtil;
import vo.OrdersInfo;
import vo.OrdersStatistics;

/**
 * Servlet implementation class OrdersServlet
 */
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrdersService ordersService = new OrdersService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void findStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<OrdersStatistics> statisticsList = ordersService.findStatistics();
		request.setAttribute("statisticsList", statisticsList);
		request.getRequestDispatcher("/admin/order_statistic.jsp").forward(request, response);
	}
	
	protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页
		String currentPageStr = request.getParameter("currentPage");
		int currentPage;
		if(currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		//获取总条数
		long count = ordersService.count(null);
		//创建Page对象
		Page<OrdersInfo> page = PageUtil.createPage(5, (int)count, currentPage);
		//分页查询
		page = ordersService.findByPage(page, null);
		request.setAttribute("ordersPage", page);
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	
	protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		//确认订单
		int result = ordersService.chg(id);
		PrintWriter out = response.getWriter();
		if(result == 1) {
    		out.print("<script>"
    				+ "alert('确认成功!');"
    				+ "window.location.href='" 
    				+ request.getContextPath() 
    				+ "/OrdersServlet?method=findByPage';"
    				+ "</script>");
		} else {
    		out.print("<script>"
    				+ "alert('确认失败!');"
    				+ "window.location.href='" 
    				+ request.getContextPath() 
    				+ "/OrdersServlet?method=findByPage';"
    				+ "</script>");
		}
		
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		//确认订单
		int result = ordersService.del(id);
		PrintWriter out = response.getWriter();
		if(result == 1) {
    		out.print("<script>"
    				+ "alert('取消成功!');"
    				+ "window.location.href='" 
    				+ request.getContextPath() 
    				+ "/OrdersServlet?method=findByPage';"
    				+ "</script>");
		} else {
    		out.print("<script>"
    				+ "alert('取消失败!');"
    				+ "window.location.href='" 
    				+ request.getContextPath() 
    				+ "/OrdersServlet?method=findByPage';"
    				+ "</script>");
		}
	}
	
	protected void findBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String userid = request.getParameter("userid");
		String menuname = request.getParameter("menuname");
		String date = request.getParameter("date");
		
		OrdersSearch search = new OrdersSearch();
		search.setUserid(userid);
		search.setMenuname(menuname);
		search.setDate(date);
		
		//获取当前页
		String currentPageStr = request.getParameter("currentPage");
		int currentPage;
		if(currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		long totalCount = ordersService.count(search);
		Page<OrdersInfo> page = PageUtil.createPage(5, (int)totalCount, currentPage);
		//查询
		page = ordersService.findByPage(page, search);
		
		request.setAttribute("orderSearchPage", page);
		request.setAttribute("search", search);
		request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("findStatistics")) {
			findStatistics(request, response);
		} else if(method.equals("findByPage")) {
			findByPage(request, response);
		} else if(method.equals("del")) {
			del(request, response);
		} else if(method.equals("chg")) {
			chg(request, response);
		} else if(method.equals("findBySearch")){
			findBySearch(request, response);
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
