<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Фермерский Маркет - ${pageTitle}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        .header { background: #4CAF50; color: white; padding: 10px 20px; }
        .nav { background: #333; overflow: hidden; }
        .nav a { float: left; color: white; text-align: center; padding: 14px 16px; text-decoration: none; }
        .nav a:hover { background-color: #ddd; color: black; }
        .nav-right { float: right; }
        .content { padding: 20px; }
        .message { padding: 10px; margin: 10px 0; border-radius: 5px; }
        .success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .info { background: #d1ecf1; color: #0c5460; border: 1px solid #bee5eb; }
        .product-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 20px; }
        .product-card { border: 1px solid #ddd; padding: 15px; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;
        }
        .btn {
            background: #4CAF50; color: white; padding: 10px 15px;
            border: none; border-radius: 4px; cursor: pointer;
        }
        .btn:hover { background: #45a049; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Фермерский Маркет</h1>
    </div>

    <div class="nav">
        <a href="${pageContext.request.contextPath}/home">Главная</a>
        <a href="${pageContext.request.contextPath}/products">Товары</a>

        <c:if test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.isSeller}">
                <a href="${pageContext.request.contextPath}/seller/products">Мои товары</a>
                <a href="${pageContext.request.contextPath}/seller/add-product">Добавить товар</a>
            </c:if>
            <c:if test="${not sessionScope.isSeller}">
                <a href="${pageContext.request.contextPath}/orders">Мои заказы</a>
            </c:if>
            <div class="nav-right">
                <span style="color: white; padding: 14px 16px;">
                    Привет, ${sessionScope.user.email}
                </span>
                <a href="${pageContext.request.contextPath}/logout">Выйти</a>
            </div>
        </c:if>

        <c:if test="${empty sessionScope.user}">
            <div class="nav-right">
                <a href="${pageContext.request.contextPath}/login">Вход</a>
                <a href="${pageContext.request.contextPath}/register">Регистрация</a>
            </div>
        </c:if>
    </div>

    <div class="content">
        <c:if test="${not empty message}">
            <div class="message ${messageType}">${message}</div>
        </c:if>