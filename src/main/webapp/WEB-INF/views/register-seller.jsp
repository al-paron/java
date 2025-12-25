<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Регистрация продавца" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Регистрация продавца</h2>

<form method="post" action="${pageContext.request.contextPath}/register-seller">
    <div class="form-group">
        <label for="farmName">Название фермы:</label>
        <input type="text" id="farmName" name="farmName" required>
    </div>

    <div class="form-group">
        <label for="description">Описание:</label>
        <textarea id="description" name="description" rows="4"></textarea>
    </div>

    <button type="submit" class="btn">Завершить регистрацию</button>
</form>

<jsp:include page="footer.jsp"/>