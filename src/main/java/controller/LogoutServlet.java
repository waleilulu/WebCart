package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet { //因為LogoutServlet會有黃底線警告，可以加上下面的public static final long serialVersionUID = 1;，或是加上@SuppressWarnings("serial")

	//public static final long serialVersionUID = 1;

	/* 精靈產生: 右鍵Source➔Override/Implement➔doGet */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 強迫將session 失效
		HttpSession session = req.getSession();
		session.invalidate();

		// 若登出成功，要帶給result.jsp的參數
		req.setAttribute("result", "登出成功");
		req.setAttribute("redirectURL", "/WebCart/login");
		req.setAttribute("redirectName", "請重新登入");

		req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
	}

}
