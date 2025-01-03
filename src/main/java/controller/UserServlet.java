package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.UserDto;
import model.service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	/*controller要跟userservice互動*/
	private UserService userService = new UserService();
	
	/*精靈產生: 右鍵➔Source➔Override&Implement➔doGet*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserDto> userDtos = userService.findAllUsers();
		req.setAttribute("userDtos", userDtos);
		// 重導到 user.jsp
		req.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
	}
	
}
