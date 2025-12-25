<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Вход" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Вход в систему</h2>

<form method="post" action="${pageContext.request.contextPath}/login">
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <button type="submit" class="btn">Войти</button>
</form>

<p style="margin-top: 20px;">
    Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрируйтесь</a>
</p>

<jsp:include page="footer.jsp"/>