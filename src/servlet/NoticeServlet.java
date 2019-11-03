package servlet;

import java.io.IOException;


import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.Comment;
import service.CommentService;

/**
 * Servlet implementation class commentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private CommentService CommentService=new CommentService();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//查询所有
    	ArrayList<Comment> commentList = commentService.findAll();
    	//把查询到的信息添加到request域中
    	request.setAttribute("commentList", commentList);
    	//转发到type.jsp
    	request.getRequestDispatcher("/admin/comment.jsp").forward(request, response);
    }
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idStr = request.getParameter("id");
    	//String--->int
    	int id = Integer.parseInt(idStr);
    	int result = commentService.del(id);
    	//获取输出流
    	PrintWriter out = response.getWriter();
    	if(result == 1) { //删除成功
    		//弹出对话框
    		//跳转—---查询所有的界面
    		out.print("<script>"
    				+ "alert('删除成功！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/CommentServlet?method=findAll';"
    				+ "</script>");
    	}else
    	{
    		out.print("<script>"
    				+ "alert('删除失败！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/CommentServlet?method=findAll';"
    				+ "</script>");
    	}
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name= request.getParameter("name");
    	String content=request.getParameter("content");
    	 Date date = new Date();
 	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	    String times = ft.format(date);
    	//String--->int
    	Comment comment=new Comment();
    	comment.setName(name);
    	comment.setContent(content);
    	comment.setTimes(times);
    	int result=commentService.add(comment);
    	PrintWriter out=response.getWriter();
        if(result == 1) { 
        	out.print("<script>"
    				+ "alert('添加成功！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/CommentServlet?method=findAll';"
    				+ "</script>");

                }
        else
           {
        	out.print(
        			"<script>"
    				+ "alert('添加失败！');"
    				+"window.location.href='"
    				+request.getContextPath()
    				+"/CommentServlet?method=findAll';"
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
		} 
		else if(method.equals("del"))
		{
			del(request, response);
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
