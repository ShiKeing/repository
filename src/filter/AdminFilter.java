package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import po.Admin;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {
   private FilterConfig config=null;
    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 1.如果没有登录，跳到登录页
		 2.已经登录---放行----如何放行
		 获取session*/
		
		String indexPath=this.config.getInitParameter("indexPath");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		//获取用户请求地址
		String path=req.getServletPath();
		if(admin !=null ||path.equals("/admin/index.jsp")||
				path.endsWith(".css")||path.endsWith(".js")||path.endsWith(".gif")||path.endsWith(".jpg"))//已经登录放行
		{
			chain.doFilter(request, response);
		}
		else//
		{
			req.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config=fConfig;
	}

}
