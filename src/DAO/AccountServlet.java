package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountServlet extends HttpServlet {
	public AccountServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("---");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String ControlerType = request.getParameter("type");
		if (ControlerType.equals("Logout")) {
			if (session.getAttribute("LoginedUserName") == null) {
				out.print("δ��¼��<br/> <a href='" + request.getContextPath()
						+ "/LoginForm.jsp' />��¼</a>");
			} else {
				session.removeAttribute("LoginedUserName");
				out.print("�Ѿ��ǳ���<br/> <a href='" + request.getContextPath()
						+ "/LoginForm.jsp' />��¼</a>");
			}
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("......");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String ControlerType = request.getParameter("type");
		if (ControlerType != null) {
			try {
				if (ControlerType.equals("Login")) {
					System.out.print("---");
					String UserName = request.getParameter("UserName");
					String PassWord = request.getParameter("PassWord");
					if (UserName == null || PassWord == null) {
						response.sendRedirect("LoginForm.jsp");
					}
					int LoginUserResult;
					LoginUserResult = DataBaseOperate.LoginUser(UserName,
							PassWord);
					if (LoginUserResult == 0) {
						session.setAttribute("LoginedUserName", request
								.getParameter("UserName"));
						response.sendRedirect(request.getContextPath()
								+ "/index.jsp");
					} else if (LoginUserResult == 1) {
						out.print("<h1>�û�������ȷ��</h1> <br /> <a href='"
								+ request.getContextPath()
								+ "/LoginForm.jsp' />���µ�¼</a>");
					} else if (LoginUserResult == 2) {
						out.print("<h1>���벻��ȷ��</h1> <br /> <a href='"
								+ request.getContextPath()
								+ "/LoginForm.jsp' />���µ�¼</a>");
					}
					out.close();
				} else if (ControlerType.equals("Register")) {
					String UserName = request.getParameter("UserName");
					String PassWord = request.getParameter("PassWord");
					if (UserName == null || PassWord == null) {
						response.sendRedirect(request.getContextPath()
								+ "RegisterForm.jsp");
					} else {
						int RegisterResult;
						RegisterResult = DataBaseOperate.RegisterUser(UserName,
								PassWord);
						if (RegisterResult == 1) {
							out.print("<h1>ע��ɹ���</h1> <br /> <a href='"
									+ request.getContextPath()
									+ "/LoginForm.jsp' />��¼</a>");
						} else if (RegisterResult == 0) {
							out.print("<h1>�û����Ѿ����ڣ�</h1> <br /> <a href='"
									+ request.getContextPath()
									+ "/RegisterForm.jsp' />����ע��</a>");
						}
						out.close();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	public void init() throws ServletException {

	}
}
