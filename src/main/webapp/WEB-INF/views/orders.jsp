<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Мои заказы" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Мои заказы</h2>

<c:choose>
    <c:when test="${empty orders}">
        <p>У вас пока нет заказов.</p>
        <a href="${pageContext.request.contextPath}/products" class="btn">Перейти к товарам</a>
    </c:when>
    <c:otherwise>
        <table style="width: 100%; border-collapse: collapse; margin-top: 20px;">
            <thead>
                <tr style="background: #f2f2f2;">
                    <th style="border: 1px solid #ddd; padding: 8px;">№ заказа</th>
                    <th style="border: 1px solid #ddd; padding: 8px;">Дата</th>
                    <th style="border: 1px solid #ddd; padding: 8px;">Адрес</th>
                    <th style="border: 1px solid #ddd; padding: 8px;">Статус</th>
                    <th style="border: 1px solid #ddd; padding: 8px;">Действия</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td style="border: 1px solid #ddd; padding: 8px;">${order.orderId}</td>
                        <td style="border: 1px solid #ddd; padding: 8px;">
                            <fmt:formatDate value="${order.createdAt}" pattern="dd.MM.yyyy HH:mm"/>
                        </td>
                        <td style="border: 1px solid #ddd; padding: 8px;">${order.addressLine}</td>
                        <td style="border: 1px solid #ddd; padding: 8px;">${order.status}</td>
                        <td style="border: 1px solid #ddd; padding: 8px;">
                            <a href="#" class="btn" style="padding: 5px 10px; font-size: 14px;">
                                Подробнее
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>