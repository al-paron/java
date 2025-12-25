<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Товары" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Товары</h2>

<!-- Форма поиска и фильтрации -->
<form method="get" action="${pageContext.request.contextPath}/products" style="margin-bottom: 20px;">
    <div style="display: flex; gap: 10px; margin-bottom: 10px;">
        <input type="text" name="search" placeholder="Поиск товаров..."
               value="${searchQuery}" style="flex: 1;">
        <button type="submit" class="btn">Поиск</button>
    </div>

    <div style="display: flex; gap: 10px; align-items: center;">
        <select name="categoryId" style="flex: 1;">
            <option value="">Все категории</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.categoryId}"
                    <c:if test="${category.categoryId == selectedCategoryId}">selected</c:if>>
                    ${category.name}
                </option>
            </c:forEach>
        </select>
        <button type="submit" class="btn">Фильтровать</button>
        <a href="${pageContext.request.contextPath}/products" class="btn">Сбросить</a>
    </div>
</form>

<!-- Список товаров -->
<c:choose>
    <c:when test="${empty products}">
        <p>Товары не найдены.</p>
    </c:when>
    <c:otherwise>
        <div class="product-grid">
            <c:forEach items="${products}" var="product">
                <div class="product-card">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p><strong>Цена:</strong> ${product.pricePerUnit} руб/${product.unit}</p>
                    <p><strong>Количество:</strong> ${product.quantity} ${product.unit}</p>
                    <p><strong>Дата производства:</strong> ${product.productionDate}</p>

                    <c:if test="${sessionScope.isSeller}">
                        <p><em>Это ваш товар</em></p>
                    </c:if>

                    <c:if test="${not sessionScope.isSeller and sessionScope.user != null}">
                        <form method="post" action="${pageContext.request.contextPath}/cart/add"
                              style="margin-top: 10px;">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <input type="number" name="quantity" value="1" min="0.1" max="${product.quantity}"
                                   step="0.1" style="width: 60px; margin-right: 5px;">
                            <button type="submit" class="btn">В корзину</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>