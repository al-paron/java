package org.web;

import org.model.Seller;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/register-seller")
public class RegisterSellerServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean registeringAsSeller = (Boolean) request.getSession().getAttribute("registeringAsSeller");
        Integer newUserId = (Integer) request.getSession().getAttribute("newUserId");

        if (registeringAsSeller == null || !registeringAsSeller || newUserId == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/register-seller.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer newUserId = (Integer) request.getSession().getAttribute("newUserId");

        if (newUserId == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        String farmName = request.getParameter("farmName");
        String description = request.getParameter("description");

        if (farmName == null || farmName.trim().isEmpty()) {
            SessionUtils.addMessage(request, "Название фермы обязательно", "error");
            response.sendRedirect(request.getContextPath() + "/register-seller");
            return;
        }

        Optional<Seller> existingSeller = sellerService.findByUserId(newUserId);
        if (existingSeller.isPresent()) {
            SessionUtils.addMessage(request, "Вы уже зарегистрированы как продавец", "error");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        org.model.Role sellerRole = new org.model.Role(null, newUserId, "SELLER", false);
        roleService.save(sellerRole);

        Seller seller = new Seller(null, newUserId, farmName, description);
        sellerService.save(seller);

        Optional<org.model.User> userOpt = userService.findSingle(newUserId);

        if (userOpt.isEmpty()) {
            SessionUtils.addMessage(request, "Ошибка при регистрации продавца", "error");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        request.getSession().removeAttribute("registeringAsSeller");
        request.getSession().removeAttribute("newUserId");

        SessionUtils.setUser(request, userOpt.get(), true);

        SessionUtils.addMessage(request, "Регистрация продавца прошла успешно!", "success");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}