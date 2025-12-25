<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Ошибка" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Ошибка ${requestScope['javax.servlet.error.status_code']}</h2>
<p>${requestScope['javax.servlet.error.message']}</p>
<p><a href="${pageContext.request.contextPath}/home">Вернуться на главную</a></p>

<jsp:include page="footer.jsp"/>