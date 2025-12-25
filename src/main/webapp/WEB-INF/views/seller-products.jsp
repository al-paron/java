<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Мои товары" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Мои товары</h2>

<a href="${pageContext.request.contextPath}/seller/add-product" class="btn"
   style="margin-bottom: 20px;">
    Добавить новый товар
</a>

<c:choose>
    <c:when test="${empty products}">
        <p>У вас пока нет товаров. <a href="${pageContext.request.contextPath}/seller/add-product">
        Добавьте первый товар</a></p>
    </c:when>
    <c:otherwise>
        <div class="product-grid">
            <c:forEach items="${products}" var="product">
                <div class="product-card">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p><strong>Цена:</strong> ${product.pricePerUnit} руб/${product.unit}</p>
                    <p><strong>Остаток:</strong> ${product.quantity} ${product.unit}</p>
                    <p><strong>Дата производства:</strong> ${product.productionDate}</p>
                    <p><strong>Обновлено:</strong> ${product.lastUpdated}</p>

                    <div style="margin-top: 10px;">
                        <a href="#" class="btn" style="background: #007bff;">Редактировать</a>
                        <a href="#" class="btn" style="background: #dc3545;">Удалить</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>