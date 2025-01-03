package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//哪一些網址需要被過濾
//@WebFilter(urlPatterns = {"/product", "/product/*", "/user"})
@WebFilter(urlPatterns = { "/*" }) // A. 這個是全網站所有也面都要登入，如果要寫這個的話，要配合下面判斷是不是登入入口
public class LoginFilter extends HttpFilter {

	/* 精靈產生: 右鍵Source➔Override/Implement➔doFilter(HttpServletResponse) */
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 若URI是"/WebCart/login" 登入頁或"/WebCart/CAPTCHA"，則by pass不用過濾
		// System.out.println(request.getRequestURI().toString()); //在Console印出來，看使用者請求的網址是什麼，/WebCart/product
		// System.out.println(request.getRequestURL().toString()); //http://localhost:8080/WebCart/product
		if (request.getRequestURI().toString().equals("/WebCart/login") || request.getRequestURI().toString().equals("/WebCart/CAPTCHA")) { // A. 登入入口要放出來讓使用者登入，沒有寫的話就會有bug: 要登入網站請先登入
			chain.doFilter(request, response);
			return;
		}

		// 檢查是否有登入資訊?
		HttpSession session = request.getSession();
		if (session.getAttribute("userDto") == null) {

			// 使用者尚未登入，要帶給result.jsp的參數
			request.setAttribute("result", "使用者尚未登入");
			request.setAttribute("redirectURL", "/WebCart/login");
			request.setAttribute("redirectName", "請登入");
			request.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(request, response);
			return;
		}

		// by pass 不用過濾
		chain.doFilter(request, response);
	}

}
