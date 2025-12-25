package org.web;

import org.model.Seller;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class SellerProductsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtils.isSeller(request)) {
            SessionUtils.addMessage(request, "Только продавцы могут просматривать свои товары", "error");
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        Integer userId = SessionUtils.getUserId(request);
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Optional<Seller> sellerOpt = sellerService.findByUserId(userId);
        if (sellerOpt.isEmpty()) {
            SessionUtils.addMessage(request, "Ошибка: продавец не найден", "error");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Seller seller = sellerOpt.get();

        var products = productService.findBySellerId(seller.getSellerId());

        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/seller-products.jsp").forward(request, response);
    }
}