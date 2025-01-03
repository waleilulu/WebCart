package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.ProductDto;
import model.service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private ProductService productService = new ProductService();

	// 右鍵 ➔ Source ➔ Override/Implement Methods ➔ 勾選doGet(HttpServletRequest, HttpServletResopnse) ➔ 按OK ➔ 會看到精靈自動產生語法
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 取得所有商品資訊
		List<ProductDto> productDtos = productService.findAll();

		// 將此商品資訊放到request屬性中
		req.setAttribute("productDtos", productDtos);

		// 重導到指定 jsp
		req.getRequestDispatcher("/WEB-INF/view/product.jsp").forward(req, resp);
	}

}
