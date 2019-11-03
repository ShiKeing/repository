package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.Types;
import service.TypesService;

/**
 * Servlet implementation class TypesServlet
 */
public class TypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypesService typesService = new TypesService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//查询所有
    	ArrayList<Types> typesList = typesService.findAll();
    	//把查询到的信息添加到request域中
    	request.setAttribute("typesList", typesList);
    	//转发到type.jsp
    	request.getRequestDispatcher("/admin/type.jsp").forward(request, response);
    }
    
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idStr = request.getParameter("id");
    	//String--->int
    	int id = Integer.parseInt(idStr);
    	int result = typesService.del(id);
    	//获取输出流
    	PrintWriter out = response.getWriter();
    	if(result == 1) { //删除成功
    		//弹出对话框
    		//跳转—---查询所有的界面
    		out.print("<script>"
    				+ "alert('删除成功！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");
    	}else
    	{
    		out.print("<script>"
    				+ "alert('删除失败！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");
    	}
    	
    }
    //根据ID查询，在修改页显示
    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//获取Id
    	String idStr = request.getParameter("id");
    	//String--->int
    	int id = Integer.parseInt(idStr);
    	//根据ID查询
    	Types types = typesService.findById(id);
    	//将查询到的内容存放在域中
    	request.setAttribute("types", types);
    	//转发到修改页
    	request.getRequestDispatcher("/admin/type_update.jsp").forward(request, response);
    }
    
    protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//获取请求参数  id,name
    	String idStr = request.getParameter("id");
    	String name= request.getParameter("name");
    	//String--->int
    	int id = Integer.parseInt(idStr);
    	//修改
    	PrintWriter out = response.getWriter();
    	int result=typesService.chg(id,name);
        if(result == 1) { //修改成功
        	out.print("<script>"
    				+ "alert('修改成功！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");
        }
        else 
        {
        	out.print("<script>"
    				+ "alert('修改失败！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");
        }
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//获取请求参数  name
    	String name= request.getParameter("name");
    	//String--->int
    	Types types=new Types();
    	types.setName(name);
    	int result=typesService.add(types);
    	PrintWriter out=response.getWriter();
        if(result == 1) { 
        	out.print("<script>"
    				+ "alert('添加成功！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");

                }
        else
           {
        	out.print(
        			"<script>"
    				+ "alert('添加失败！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/TypesServlet?method=findAll';"
    				+ "</script>");
            }
         }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("findAll")) {
			findAll(request, response);
		} else if(method.equals("del")) {
			del(request, response);
		} else if(method.equals("findById")) {
			findById(request, response);
		} else if(method.equals("chg")) {
			chg(request, response);
		}
		else if(method.equals("add"))
		{
			add(request, response);
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