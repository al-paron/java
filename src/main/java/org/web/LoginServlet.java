package org.web;

import org.model.User;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (SessionUtils.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            SessionUtils.addMessage(request, "Все поля обязательны для заполнения", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String passwordHash = Integer.toHexString(password.hashCode());

        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            SessionUtils.addMessage(request, "Пользователь с таким email не найден", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = userOpt.get();

        if (!user.getPasswordHash().equals(passwordHash)) {
            SessionUtils.addMessage(request, "Неверный пароль", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (user.isDeleted()) {
            SessionUtils.addMessage(request, "Аккаунт заблокирован", "error");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        boolean isSeller = roleService.isUserSeller(user.getUserId());

        SessionUtils.setUser(request, user, isSeller);

        SessionUtils.addMessage(request, "Вход выполнен успешно!", "success");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}