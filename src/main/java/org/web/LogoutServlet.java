package org.web;

import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionUtils.clearSession(request);
        SessionUtils.addMessage(request, "Вы успешно вышли из системы", "success");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}