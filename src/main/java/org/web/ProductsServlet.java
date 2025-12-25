package org.web;

import org.model.Category;
import org.model.Product;
import org.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ProductsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdParam = request.getParameter("categoryId");
        String searchQuery = request.getParameter("search");

        List<Product> products;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                Integer categoryId = Integer.parseInt(categoryIdParam);
                products = productService.findByCategoryId(categoryId);
            } catch (NumberFormatException e) {
                products = productService.findAll();
            }
        } else if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            products = productService.findAll().stream()
                    .filter(p -> p.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .toList();
        } else {
            products = productService.findAll();
        }

        List<Category> categories = categoryService.findAll();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("selectedCategoryId", categoryIdParam);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(request, response);
    }
}