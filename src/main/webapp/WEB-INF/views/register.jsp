<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Регистрация" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Регистрация</h2>

<form method="post" action="${pageContext.request.contextPath}/register">
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="phone">Телефон:</label>
        <input type="tel" id="phone" name="phone" required>
    </div>

    <div class="form-group">
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div class="form-group">
        <label for="confirmPassword">Подтвердите пароль:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>

    <div class="form-group">
        <label>
            <input type="checkbox" name="wantsToBeSeller" value="true">
            Хочу зарегистрироваться как продавец
        </label>
    </div>

    <button type="submit" class="btn">Зарегистрироваться</button>
</form>

<p style="margin-top: 20px;">
    Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войдите</a>
</p>

<jsp:include page="footer.jsp"/>