package org.web;

import org.model.User;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/register")
public class RegisterServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (SessionUtils.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (email == null || email.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                confirmPassword == null || confirmPassword.trim().isEmpty()) {

            SessionUtils.addMessage(request, "Все поля обязательны для заполнения", "error");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        if (!password.equals(confirmPassword)) {
            SessionUtils.addMessage(request, "Пароли не совпадают", "error");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        if (userService.findByEmail(email).isPresent()) {
            SessionUtils.addMessage(request, "Пользователь с таким email уже существует", "error");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        String passwordHash = Integer.toHexString(password.hashCode());

        User newUser = new User(null, email, phone, passwordHash);
        newUser.setCreatedAt(Timestamp.from(Instant.now()));

        userService.save(newUser);

        Optional<User> savedUser = userService.findByEmail(email);

        if (savedUser.isEmpty()) {
            SessionUtils.addMessage(request, "Ошибка при регистрации", "error");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }


        String wantsToBeSeller = request.getParameter("wantsToBeSeller");

        if ("true".equals(wantsToBeSeller)) {
            request.getSession().setAttribute("registeringAsSeller", true);
            request.getSession().setAttribute("newUserId", savedUser.get().getUserId());
            response.sendRedirect(request.getContextPath() + "/register-seller");
            return;
        }

        SessionUtils.setUser(request, savedUser.get(), false);

        SessionUtils.addMessage(request, "Регистрация прошла успешно!", "success");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}