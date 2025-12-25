<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Добавить товар" scope="request"/>
<jsp:include page="header.jsp"/>

<h2>Добавить товар</h2>

<form method="post" action="${pageContext.request.contextPath}/seller/add-product">
    <div class="form-group">
        <label for="name">Название товара:</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="description">Описание:</label>
        <textarea id="description" name="description" rows="3"></textarea>
    </div>

    <div class="form-group">
        <label for="categoryId">Категория:</label>
        <select id="categoryId" name="categoryId" required>
            <option value="">Выберите категорию</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.categoryId}">${category.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="price">Цена за единицу:</label>
        <input type="number" id="price" name="price" min="0" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="unit">Единица измерения:</label>
        <select id="unit" name="unit" required>
            <option value="">Выберите единицу</option>
            <option value="кг">кг</option>
            <option value="шт">шт</option>
            <option value="литр">литр</option>
            <option value="упаковка">упаковка</option>
        </select>
    </div>

    <div class="form-group">
        <label for="quantity">Количество:</label>
        <input type="number" id="quantity" name="quantity" min="0" step="0.1" required>
    </div>

    <div class="form-group">
        <label for="productionDate">Дата производства:</label>
        <input type="date" id="productionDate" name="productionDate" required>
    </div>

    <button type="submit" class="btn">Добавить товар</button>
    <a href="${pageContext.request.contextPath}/seller/products" class="btn">Отмена</a>
</form>

<jsp:include page="footer.jsp"/>