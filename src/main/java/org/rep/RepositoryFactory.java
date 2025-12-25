package org.rep;

import org.rep.inmemory.*;
import org.rep.jdbc.*;

public class RepositoryFactory {
    private static boolean useJdbc = true; // Флаг для переключения между in-memory и JDBC

    public static void setUseJdbc(boolean useJdbc) {
        RepositoryFactory.useJdbc = useJdbc;
    }

    public static UserRep createUserRep() {
        return useJdbc ? new JdbcUserRep() : new UserRepo();
    }

    public static RoleRep createRoleRep() {
        return useJdbc ? new JdbcRoleRep() : new RoleRepo();
    }

    public static SellerRep createSellerRep() {
        return useJdbc ? new JdbcSellerRep() : new SellerRepo();
    }

    public static CategoryRep createCategoryRep() {
        return useJdbc ? new JdbcCategoryRep() : new CategoryRepo();
    }

    public static ProductRep createProductRep() {
        return useJdbc ? new JdbcProductRep() : new ProductRepo();
    }

    public static OrderRep createOrderRep() {
        return useJdbc ? new JdbcOrderRep() : new OrderRepo();
    }

    public static OrderItemRep createOrderItemRep() {
        return useJdbc ? new JdbcOrderItemRep() : new OrderItemRepo();
    }

    public static ReviewRep createReviewRep() {
        return useJdbc ? new JdbcReviewRep() : new ReviewRepo();
    }
}