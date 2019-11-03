package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import po.Menus;
import po.Page;
import po.Types;
import service.MenusService;
import service.TypesService;
import util.PageUtil;
import vo.MenusInfo;

/**
 * Servlet implementation class MenusServlet
 */
public class MenusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenusService menusService = new MenusService();
	private TypesService typesService = new TypesService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenusServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 页数---->从浏览器传过来的(以参数的形式传过来的)
		String currentPageStr = request.getParameter("currentPage");
		int currentPage;
		// 如果没有currentPage,默认查询第一页
		if (currentPageStr == null) {
			currentPage = 1;
		} else {
			// String-->int
			currentPage = Integer.parseInt(currentPageStr);
		}
		// 总条数
		long totalCount = menusService.count();
		// 创建一个Page对象 1.每页显示的条数 2.总条数 3.页数
		Page<MenusInfo> page = PageUtil.createPage(5, (int) totalCount, currentPage);
		// 分页查询 menusService.findByPage(page);
		page = menusService.findByPage(page);
		// 把page保存在域中
		request.setAttribute("menusPage", page);
		// 转发到menus.jsp
		request.getRequestDispatcher("/admin/menus.jsp").forward(request, response);
	}

	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		// String--->int
		int id = Integer.parseInt(idStr);
		int result = menusService.del(id);
		PrintWriter out = response.getWriter();
		if (result == 1) {

			out.print("<script>" + "alert('删除成功！');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage';" + "</script>");
		} else {
			out.print("<script>" + "alert('删除失败！');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage';" + "</script>");
		}

	}

	protected void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		// 根据ID查询Menus
		Menus menu = menusService.findById(id);
		// 查询所有的菜品类别
		ArrayList<Types> typeList = typesService.findAll();
		// 将查询到的内容存放在域对象中
		request.setAttribute("menu", menu);
		request.setAttribute("typeList", typeList);
		// 转发到menus_update.jsp
		request.getRequestDispatcher("/admin/menus_update.jsp").forward(request, response);
	}

	protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String name = request.getParameter("name");
		String burden = request.getParameter("burden");
		String price = request.getParameter("price");
		String price1 = request.getParameter("price1");
		String brief = request.getParameter("brief");
		String typeid = request.getParameter("typeid");
		Menus menu = new Menus();
		menu.setName(name);
		menu.setBurden(burden);
		menu.setPrice(price);
		menu.setBrief(brief);
		menu.setPrice1(price1);
		menu.setTypeid(typeid);
		int result = menusService.chg(id, menu);
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("<script>" + "alert('修改成功！');"
			       + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage';" + "</script>");
		} else {
			out.print("<script>" + "alert('修改失败！');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage';" + "</script>");
		}
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取请求参数， 保存menus表中
		 * 2.保存上传的文件
		 * */
		//新建SmartUpload对象
		SmartUpload smartUpload = new SmartUpload();
		ServletConfig config = this.getServletConfig();
		//初始化配置
		smartUpload.initialize(config, request, response);
		//获取请求对象
		SmartRequest req = smartUpload.getRequest();

		//上传图片
		try {
			smartUpload.upload();
			SmartFile picFile = smartUpload.getFiles().getFile(0);

			//获取请求参数
			String name = req.getParameter("name");
			String burden = req.getParameter("burden");
			String price = req.getParameter("price");
			String price1 = req.getParameter("price1");
			String brief = req.getParameter("brief");
			String typeid = req.getParameter("typeid");
			String imgpath = "img/" + picFile.getFileName();
			
			Menus menu = new Menus();
			menu.setName(name);
			menu.setBurden(burden);
			menu.setPrice(price1);
			menu.setPrice1(price1);
			menu.setBrief(brief);
			menu.setTypeid(typeid);
			menu.setImgpath(imgpath);
			
			int result = menusService.add(menu);
			PrintWriter out = response.getWriter();
			if(result == 1) {
				smartUpload.save("img/");
				out.print("<script>"
						+ "alert('添加成功');" 
						+ "window.location.href='" 
						+ request.getContextPath()
						+ "/MenusServlet?method=findByPage';" 
						+ "</script>");
			} else {
				out.print("<script>"
						+ "alert('添加失败');" 
						+ "window.location.href='" 
						+ request.getContextPath()
						+ "/MenusServlet?method=findByPage';" 
						+ "</script>");
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	protected void preAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ArrayList<Types> typeList = typesService.findAll();
		request.setAttribute("typesList", typeList);
		request.getRequestDispatcher("/admin/menus_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("findByPage"))
				{
			findByPage(request, response);
				}
		else if(method.equals("del"))
		{
			del(request, response);
		}
		else if(method.equals("findById"))
		{
			findById(request, response);
		}
		else if (method.equals("chg"))
		{
			chg(request, response);
		}
		else if(method.equals("add"))
		{
			add(request, response);
		}
		else if(method.equals("preAdd"))
		{
			preAdd(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
