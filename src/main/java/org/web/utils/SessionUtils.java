package org.web.utils;

import org.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    private static final String USER_ATTRIBUTE = "user";
    private static final String IS_SELLER_ATTRIBUTE = "isSeller";
    private static final String USER_ID_ATTRIBUTE = "userId";

    public static void setUser(HttpServletRequest request, User user, boolean isSeller) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ATTRIBUTE, user);
        session.setAttribute(IS_SELLER_ATTRIBUTE, isSeller);
        session.setAttribute(USER_ID_ATTRIBUTE, user.getUserId());
    }

    public static User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (User) session.getAttribute(USER_ATTRIBUTE) : null;
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getUser(request) != null;
    }

    public static boolean isSeller(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && Boolean.TRUE.equals(session.getAttribute(IS_SELLER_ATTRIBUTE));
    }

    public static Integer getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (Integer) session.getAttribute(USER_ID_ATTRIBUTE) : null;
    }

    public static void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static void addMessage(HttpServletRequest request, String message, String type) {
        HttpSession session = request.getSession(true);
        session.setAttribute("message", message);
        session.setAttribute("messageType", type);
    }

    public static String getAndClearMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String message = (String) session.getAttribute("message");
            session.removeAttribute("message");
            session.removeAttribute("messageType");
            return message;
        }
        return null;
    }
}