package org.web;

import org.service.inmemory.*;
import org.rep.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected UserService userService;
    protected RoleService roleService;
    protected SellerService sellerService;
    protected CategoryService categoryService;
    protected ProductService productService;
    protected OrderService orderService;
    protected OrderItemService orderItemService;
    protected ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        super.init();

        userService = new UserService(RepositoryFactory.createUserRep());
        roleService = new RoleService(RepositoryFactory.createRoleRep());
        sellerService = new SellerService(RepositoryFactory.createSellerRep());
        categoryService = new CategoryService(RepositoryFactory.createCategoryRep());
        productService = new ProductService(RepositoryFactory.createProductRep());
        orderService = new OrderService(RepositoryFactory.createOrderRep());
        orderItemService = new OrderItemService(RepositoryFactory.createOrderItemRep());
        reviewService = new ReviewService(RepositoryFactory.createReviewRep());
    }
}