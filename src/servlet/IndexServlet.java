package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.CarItem;
import po.Menus;
import po.Page;
import service.MenusService;
import service.OrdersService;
import util.PageUtil;
import vo.MenusInfo;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();
	private MenusService menusService = new MenusService();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void allInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//menu
		String currentPageStr = request.getParameter("currentPage");
		int currentPage;
		if(currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		long totalCount = menusService.count();
		Page<MenusInfo> page = PageUtil.createPage(6, (int)totalCount, currentPage);
		//查询menus
		page = menusService.findByPage(page);
		request.setAttribute("menusPage", page);
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
	}
	protected void removeone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取menu的id---->请求参数
		 * 2.获取表示餐车的对象
		 * 	 |----如果获取不到餐车
		 * 			1)创建餐车
		 * 			2)根据id查询menu
		 * 			3)根据menu创建CarItem的对象，并将数量设置成1
		 * 			4)将CarItem添加到餐车中
		 * 			5)将餐车添加到session中
		 * 	 |----如果获取到了餐车
		 * 			根据id查询是否有对应的CarItem---->遍历
		 * 				|---找到了，将对应的CarItem数量加1
		 * 				|---找不到
		 * 					  1)根据id查询menu
		 * 					  2)根据menu创建CarItem的对象，并将数量设置成1
		 * 
		 * */
		//获取menu的id
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		//获取表示餐车的对象
		HttpSession session = request.getSession();
		ArrayList<CarItem> carList = (ArrayList<CarItem>) session.getAttribute("carList");
		if(carList == null) { //获取不到餐车
			//创建餐车
			carList = new ArrayList<CarItem>();
			//根据id查询menu
			Menus menus = menusService.findById(id);
			//根据menu创建CarItem的对象，并将数量设置成1
			CarItem item = new CarItem();
			item.setMenusid(id);
			item.setMenusname(menus.getName());
			item.setPrice(menus.getPrice());
			item.setCount(1);
			//将CarItem添加到餐车中
			carList.add(item);
			//将餐车添加到session中
			session.setAttribute("carList", carList);
		} else {//获取到了餐车
			boolean flag = false;//假设没有找到
			for (CarItem carItem : carList) {
				if(carItem.getMenusid() == id) { //找到了
					flag = true;
					carItem.setCount(carItem.getCount() + 1);//在先前的数量上加1
					break;
				}
			}
			
			if(flag == false) { //确实没有找到
				Menus menus = menusService.findById(id);
				//根据menu创建CarItem的对象，并将数量设置成1
				CarItem item = new CarItem();
				item.setMenusid(id);
				item.setMenusname(menus.getName());
				item.setPrice(menus.getPrice());
				item.setCount(1);
				//将CarItem添加到餐车中
				carList.add(item);
			}
		}
		allInfo(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("allInfo")) {
			allInfo(request, response);
		} else if(method.equals("addItem")) {
			addItem(request, response);
		}
		else if(method.equals("removeone")) {
			removeone(request, response);
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
