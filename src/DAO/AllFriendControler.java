package DAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllFriendControler extends HttpServlet {
	public AllFriendControler() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ControlerType = request.getParameter("type");
		if (ControlerType.equals("all")) {
			response.sendRedirect(request.getContextPath() + "/allfriend.jsp");
		} else if (ControlerType.equals("query")) {
			response
					.sendRedirect(request.getContextPath() + "/querybyname.jsp");
		} else if (ControlerType.equals("insert")) {
			response.sendRedirect(request.getContextPath()
					+ "/InsertNewRowForm.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();// Ìí¼Ó
		String ControlerType = request.getParameter("type");
		if (ControlerType.equals("DataInsert")) {
			try {
				request.setCharacterEncoding("utf-8");
				String NewName = request.getParameter("NewName");
				String NewSex = request.getParameter("NewSex");
				String NewAge = request.getParameter("NewAge");
				String NewQQ = request.getParameter("NewQQ");
				String NewTel = request.getParameter("NewTel");
				String NewMail = request.getParameter("NewMail");
				String NewAddr = request.getParameter("NewAddr");
				if (NewName == null || NewSex == null || NewAge == null
						|| NewQQ == null || NewTel == null || NewMail == null
						|| NewAddr == null) {
					RequestDispatcher rd = request
							.getRequestDispatcher("/InsertNewRowForm.jsp");
					rd.forward(request, response);
				}
				DataBaseOperate.Insert((String) session
						.getAttribute("LoginedUserName"), NewName, NewSex,
						NewAge, NewQQ, NewTel, NewMail, NewAddr);
				response.sendRedirect(request.getContextPath()
						+ "/allfriend.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (ControlerType.equals("DataModify")) {// ÐÞ¸Ä
			try {
				request.setCharacterEncoding("utf-8");
				String NewName = request.getParameter("NewName");
				String NewSex = request.getParameter("NewSex");
				String NewAge = request.getParameter("NewAge");
				String NewQQ = request.getParameter("NewQQ");
				String NewTel = request.getParameter("NewTel");
				String NewMail = request.getParameter("NewMail");
				String NewAddr = request.getParameter("NewAddr");
				if (NewName == null || NewSex == null || NewAge == null
						|| NewQQ == null || NewTel == null || NewMail == null
						|| NewAddr == null) {
					response.sendRedirect(request.getContextPath()
							+ "/ModifyForm.jsp");
					return;
				}
				DataBaseOperate.Modify(request.getParameter("FriendId"),
						NewName, NewSex, NewAge, NewQQ, NewTel, NewMail,
						NewAddr);
				response.sendRedirect(request.getContextPath()
						+ "/allfriend.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (ControlerType.equals("DeleteOrModify")) {// É¾³ý
			String SubmitOperate = request.getParameter("SubmitOperate");
			if (SubmitOperate != null) {
				try {
					if (SubmitOperate.equals("Modify")) {
						String id = request.getParameter("Modify");
						System.out.println(id);
						response.sendRedirect(request.getContextPath()
								+ "/ModifyForm.jsp?id=" + id);
					} else if (SubmitOperate.equals("Delete")) {
						String[] CheckBoxs = request
								.getParameterValues("Delete");
						if (CheckBoxs != null) {
							for (int i = 0; i < CheckBoxs.length; i++) {
								DataBaseOperate.DeleteFriend(CheckBoxs[i]);
							}
						}
						response.sendRedirect(request.getContextPath()
								+ "/allfriend.jsp");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void init() throws ServletException {

	}
}
