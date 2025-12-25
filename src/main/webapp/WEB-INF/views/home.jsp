<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Главная" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Добро пожаловать на Фермерский Маркет!</h2>
<p>Покупайте свежие фермерские продукты напрямую у производителей.</p>

<c:if test="${empty sessionScope.user}">
    <div style="margin-top: 30px;">
        <h3>Начните прямо сейчас!</h3>
        <p>
            <a href="${pageContext.request.contextPath}/register" class="btn">Зарегистрироваться</a>
            <a href="${pageContext.request.contextPath}/login" class="btn">Войти</a>
        </p>
    </div>
</c:if>

<c:if test="${not empty sessionScope.user}">
    <div style="margin-top: 30px;">
        <h3>Что вы хотите сделать?</h3>
        <p>
            <a href="${pageContext.request.contextPath}/products" class="btn">Просмотреть товары</a>

            <c:if test="${sessionScope.isSeller}">
                <a href="${pageContext.request.contextPath}/seller/products" class="btn">Мои товары</a>
                <a href="${pageContext.request.contextPath}/seller/add-product" class="btn">Добавить товар</a>
            </c:if>

            <c:if test="${not sessionScope.isSeller}">
                <a href="${pageContext.request.contextPath}/orders" class="btn">Мои заказы</a>
            </c:if>
        </p>
    </div>
</c:if>

<jsp:include page="footer.jsp"/>