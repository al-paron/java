package org.web;

import org.model.Product;
import org.model.Seller;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;


public class AddProductServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtils.isSeller(request)) {
            SessionUtils.addMessage(request, "Только продавцы могут добавлять товары", "error");
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        request.setAttribute("categories", categoryService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/add-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtils.isSeller(request)) {
            SessionUtils.addMessage(request, "Только продавцы могут добавлять товары", "error");
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        Integer userId = SessionUtils.getUserId(request);
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Optional<Seller> sellerOpt = sellerService.findByUserId(userId);
        if (sellerOpt.isEmpty()) {
            SessionUtils.addMessage(request, "Ошибка: продавец не найден", "error");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Seller seller = sellerOpt.get();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String categoryIdParam = request.getParameter("categoryId");
        String priceParam = request.getParameter("price");
        String unit = request.getParameter("unit");
        String quantityParam = request.getParameter("quantity");
        String productionDateParam = request.getParameter("productionDate");

        // Валидация
        if (name == null || name.trim().isEmpty() ||
                categoryIdParam == null || categoryIdParam.trim().isEmpty() ||
                priceParam == null || priceParam.trim().isEmpty() ||
                unit == null || unit.trim().isEmpty() ||
                quantityParam == null || quantityParam.trim().isEmpty() ||
                productionDateParam == null || productionDateParam.trim().isEmpty()) {

            SessionUtils.addMessage(request, "Все поля обязательны для заполнения", "error");
            response.sendRedirect(request.getContextPath() + "/seller/add-product");
            return;
        }

        try {
            Integer categoryId = Integer.parseInt(categoryIdParam);
            Float price = Float.parseFloat(priceParam);
            Float quantity = Float.parseFloat(quantityParam);
            Timestamp productionDate = Timestamp.valueOf(productionDateParam + " 00:00:00");

            Product product = new Product(
                    null,
                    seller.getSellerId(),
                    categoryId,
                    name,
                    description,
                    price,
                    unit,
                    quantity,
                    productionDate
            );

            productService.save(product);

            SessionUtils.addMessage(request, "Товар успешно добавлен!", "success");
            response.sendRedirect(request.getContextPath() + "/seller/products");

        } catch (NumberFormatException e) {
            SessionUtils.addMessage(request, "Некорректные числовые значения", "error");
            response.sendRedirect(request.getContextPath() + "/seller/add-product");
        } catch (IllegalArgumentException e) {
            SessionUtils.addMessage(request, "Некорректный формат даты", "error");
            response.sendRedirect(request.getContextPath() + "/seller/add-product");
        }
    }
}