package DAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest mServletRequest,
			ServletResponse mServletResponse, FilterChain mFilterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) mServletRequest;
		HttpServletResponse response = (HttpServletResponse) mServletResponse;
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (session != null) {
			String RequestURI = request.getRequestURI();
			String ContextPath = request.getContextPath();
			Object LoginedUserName = session.getAttribute("LoginedUserName");
			if (LoginedUserName == null) {
				if (RequestURI.equals(ContextPath + "/LoginForm.jsp")
						|| RequestURI.equals(ContextPath + "/RegisterForm.jsp")
						|| RequestURI.equals(ContextPath
								+ "/servlet/AccountServlet")) {
					mFilterChain.doFilter(mServletRequest, mServletResponse);
				} else {
					out.print("ÇëÏÈµÇÂ¼£¡<br/> <a href='" + ContextPath
							+ "/LoginForm.jsp' />µÇÂ¼</a>");
					out.close();
				}
			} else {
				if (RequestURI.equals(ContextPath + "/LoginForm.jsp")) {
					out.print("ÄúÒÑ¾­µÇÂ¼ÁË£¡<br/> <a href='index.jsp' />Ö÷Ò³</a>");
					out.close();
				} else {
					mFilterChain.doFilter(mServletRequest, mServletResponse);
				}
			}
		} else {
			throw new ServletException();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
