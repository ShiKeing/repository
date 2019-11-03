package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.ZhuCe;
import service.ZhuCeService;

/**
 * Servlet implementation class zhuceServlet
 */
public class zhuceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ZhuCeService zhuCeService=new ZhuCeService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zhuceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
protected void zhuce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String idStr = request.getParameter("id");
	int id = Integer.parseInt(idStr);
	String name = request.getParameter("name");
	String pwd= request.getParameter("pwd");
	String realname = request.getParameter("realname");
	String sex = request.getParameter("sex");
	String age = request.getParameter("age");
	String card= request.getParameter("card");
	String address= request.getParameter("address");
	String phone = request.getParameter("phone");
	String email= request.getParameter("email");
	String code = request.getParameter("code");
	String type = request.getParameter("type");
	ZhuCe zhuce= new ZhuCe();
	zhuce.setId(id);
	zhuce.setName(name);
	zhuce.setPwd(pwd);
	zhuce.setRealname(realname);
	zhuce.setSex(sex);
	zhuce.setAge(age);
	zhuce.setCard(card);
	zhuce.setAddress(address);
	zhuce.setPhone(phone);
	zhuce.setEmail(email);
	zhuce.setCode(code);
	zhuce.setType(type);
	int result=ZhuCeService.add(zhuce);
	PrintWriter out=response.getWriter();
    if(result == 1) { 
    	out.print("<script>"
				+ "alert('注册成功！');"
				+"window.location.href='"
				+request.getContextPath()
				+"/qiantai/login.jsp';"
				+ "</script>");

            }
    else
       {
    	out.print(
    			"<script>"
				+ "alert('注册失败,请重新注册！');"
				+"window.location.href='"
				+request.getContextPath()
				+"/zhuceServlet?method=zhuce';"
				+ "</script>");
        }
}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("zhuce"))
		{
			zhuce(request, response);
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
