package org.web;

import org.model.Order;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtils.isLoggedIn(request)) {
            SessionUtils.addMessage(request, "Для просмотра заказов необходимо войти в систему", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Integer userId = SessionUtils.getUserId(request);

        List<Order> orders = orderService.findAll().stream()
                .filter(order -> order.getCustomerId().equals(userId))
                .toList();

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }
}