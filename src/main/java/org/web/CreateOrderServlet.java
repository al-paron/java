package org.web;

import org.model.Order;
import org.model.OrderItem;
import org.model.Product;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@WebServlet("/create-order")
public class CreateOrderServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtils.isLoggedIn(request)) {
            SessionUtils.addMessage(request, "Для создания заказа необходимо войти в систему", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Integer userId = SessionUtils.getUserId(request);
        String addressLine = request.getParameter("address");

        if (addressLine == null || addressLine.trim().isEmpty()) {
            SessionUtils.addMessage(request, "Адрес доставки обязателен", "error");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // TODO: логика получения товаров из корзины

        Order order = new Order(null, userId, addressLine, "NEW");
        order.setCreatedAt(Timestamp.from(Instant.now()));
        orderService.save(order);

        SessionUtils.addMessage(request, "Заказ успешно создан!", "success");
        response.sendRedirect(request.getContextPath() + "/orders");
    }
}