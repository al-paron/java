package org;

import org.model.*;
import org.rep.*;
import org.rep.inmemory.*;
import org.service.inmemory.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;


public class Console {
    private final UserService userService;
    private final RoleService roleService;
    private final SellerService sellerService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ReviewService reviewService;

    private Integer currentUserId;
    private boolean isSeller = false;

    public Console() {
        UserRep userRepo = new UserRepo();
        RoleRep roleRepo = new RoleRepo();
        SellerRep sellerRepo = new SellerRepo();
        CategoryRep categoryRepo = new CategoryRepo();
        ProductRep productRepo = new ProductRepo();
        OrderRep orderRepo = new OrderRepo();
        OrderItemRep orderItemRepo = new OrderItemRepo();
        ReviewRep reviewRepo = new ReviewRepo();

        this.userService = new UserService(userRepo);
        this.roleService = new RoleService(roleRepo);
        this.sellerService = new SellerService(sellerRepo);
        this.categoryService = new CategoryService(categoryRepo);
        this.productService = new ProductService(productRepo);
        this.orderService = new OrderService(orderRepo);
        this.orderItemService = new OrderItemService(orderItemRepo);
        this.reviewService = new ReviewService(reviewRepo);
    }

    private void initializeData() {
        User admin = new User(1, "admin@market.ru", "+79991112233", "admin_hash");
        userService.save(admin);

        Role adminRole = new Role(1, 1, "ADMIN", false);
        roleService.save(adminRole);

        Category vegetables = new Category(1, "Овощи", "Свежие овощи с фермы");
        Category fruits = new Category(2, "Фрукты", "Сезонные фрукты");
        Category dairy = new Category(3, "Молочные продукты", "Натуральные молочные продукты");

        categoryService.save(vegetables);
        categoryService.save(fruits);
        categoryService.save(dairy);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUserId == null) {
                showMainMenu(scanner);
            } else {
                if (isSeller) {
                    showSellerMenu(scanner);
                } else {
                    showCustomerMenu(scanner);
                }
            }
        }
    }

    private void showMainMenu(Scanner scanner) {
        System.out.println("\n=== Маркетплейс фермерских продуктов ===");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("3. Просмотр товаров");
        System.out.println("4. Выход");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> registerUser(scanner);
            case 2 -> loginUser(scanner);
            case 3 -> browseProducts();
            case 4 -> {
                System.out.println("До свидания!");
                System.exit(0);
            }
            default -> System.out.println("Неверный выбор");
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.println("\n=== Регистрация ===");

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        String passwordHash = Integer.toHexString(password.hashCode());

        int newUserId = userService.findAll().size() + 1;

        User newUser = new User(newUserId, email, phone, passwordHash);
        userService.save(newUser);

        System.out.println("Регистрация успешна! Ваш ID: " + newUserId);

        System.out.print("Хотите зарегистрироваться как продавец? (Y/N): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Y")) {
            registerAsSeller(scanner, newUserId);
        }
    }

    private void registerAsSeller(Scanner scanner, Integer userId) {
        System.out.println("\n=== Регистрация продавца ===");

        System.out.print("Введите название фермы: ");
        String farmName = scanner.nextLine();

        System.out.print("Введите описание: ");
        String description = scanner.nextLine();

        int newRoleId = roleService.findAll().size() + 1;
        Role sellerRole = new Role(newRoleId, userId, "SELLER", false);
        roleService.save(sellerRole);

        int newSellerId = sellerService.findAll().size() + 1;
        Seller seller = new Seller(newSellerId, userId, farmName, description);
        sellerService.save(seller);

        System.out.println("Регистрация продавца успешна!");
    }

    private void loginUser(Scanner scanner) {
        System.out.println("\n=== Вход ===");

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        String passwordHash = Integer.toHexString(password.hashCode());

        User foundUser = userService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (foundUser != null && foundUser.getPasswordHash().equals(passwordHash)) {
            currentUserId = foundUser.getUserId();
            isSeller = roleService.isUserSeller(currentUserId);

            System.out.println("Вход успешен! Добро пожаловать, " + email);
        } else {
            System.out.println("Неверные учетные данные");
        }
    }

    private void browseProducts() {
        System.out.println("\n=== Товары ===");

        var products = productService.findAll();
        if (products.isEmpty()) {
            System.out.println("Товаров пока нет");
        } else {
            products.forEach(product -> {
                System.out.println("ID: " + product.getProductId());
                System.out.println("Название: " + product.getName());
                System.out.println("Цена: " + product.getPricePerUnit() + " руб/" + product.getUnit());
                System.out.println("Количество: " + product.getQuantity() + " " + product.getUnit());
                System.out.println("---");
            });
        }
    }

    private void showCustomerMenu(Scanner scanner) {
        System.out.println("\n=== Покупатель ===");
        System.out.println("1. Просмотреть товары");
        System.out.println("2. Создать заказ");
        System.out.println("3. Мои заказы");
        System.out.println("4. Оставить отзыв");
        System.out.println("5. Выйти из аккаунта");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> browseProducts();
            case 2 -> createOrder(scanner);
            case 3 -> viewMyOrders();
            case 4 -> createReview(scanner);
            case 5 -> {
                currentUserId = null;
                isSeller = false;
                System.out.println("Вы вышли из аккаунта");
            }
            default -> System.out.println("Неверный выбор");
        }
    }

    private void showSellerMenu(Scanner scanner) {
        System.out.println("\n=== Продавец ===");
        System.out.println("1. Добавить товар");
        System.out.println("2. Мои товары");
        System.out.println("3. Мои продажи");
        System.out.println("4. Отзывы о моих товарах");
        System.out.println("5. Выйти из аккаунта");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addProduct(scanner);
            case 2 -> viewMyProducts();
            case 3 -> viewMySales();
            case 4 -> viewMyReviews();
            case 5 -> {
                currentUserId = null;
                isSeller = false;
                System.out.println("Вы вышли из аккаунта");
            }
            default -> System.out.println("Неверный выбор");
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.println("\n=== Добавление товара ===");

        var sellerOpt = sellerService.findByUserId(currentUserId);
        if (sellerOpt.isEmpty()) {
            System.out.println("Вы не зарегистрированы как продавец");
            return;
        }

        Seller seller = sellerOpt.get();

        System.out.println("Доступные категории:");
        categoryService.findAll().forEach(cat ->
                System.out.println(cat.getCategoryId() + ". " + cat.getName()));

        System.out.print("Выберите категорию (ID): ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Название товара: ");
        String name = scanner.nextLine();

        System.out.print("Описание: ");
        String description = scanner.nextLine();

        System.out.print("Цена за единицу: ");
        float price = scanner.nextFloat();

        scanner.nextLine();

        System.out.print("Единица измерения (кг, шт, литр): ");
        String unit = scanner.nextLine();

        System.out.print("Количество: ");
        float quantity = scanner.nextFloat();

        int newProductId = productService.findAll().size() + 1;

        Product product = new Product(
                newProductId,
                seller.getSellerId(),
                categoryId,
                name,
                description,
                price,
                unit,
                quantity,
                Timestamp.from(Instant.now())
        );

        productService.save(product);
        System.out.println("Товар успешно добавлен!");
    }

    private void viewMyProducts() {
        System.out.println("\n=== Мои товары ===");

        var sellerOpt = sellerService.findByUserId(currentUserId);
        if (sellerOpt.isEmpty()) {
            System.out.println("Вы не зарегистрированы как продавец");
            return;
        }

        Seller seller = sellerOpt.get();
        var products = productService.findBySellerId(seller.getSellerId());

        if (products.isEmpty()) {
            System.out.println("У вас пока нет товаров");
        } else {
            products.forEach(product -> {
                System.out.println("ID: " + product.getProductId());
                System.out.println("Название: " + product.getName());
                System.out.println("Цена: " + product.getPricePerUnit() + " руб/" + product.getUnit());
                System.out.println("Остаток: " + product.getQuantity() + " " + product.getUnit());
                System.out.println("---");
            });
        }
    }

    private void createOrder(Scanner scanner) {
        System.out.println("\n=== Создание заказа ===");

        browseProducts();

        System.out.print("Введите ID товара для заказа: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Количество: ");
        float quantity = scanner.nextFloat();
        scanner.nextLine();

        var productOpt = productService.findSingle(productId);
        if (productOpt.isEmpty()) {
            System.out.println("Товар не найден");
            return;
        }

        Product product = productOpt.get();
        if (product.getQuantity() < quantity) {
            System.out.println("Недостаточно товара в наличии");
            return;
        }

        product.setQuantity(product.getQuantity() - quantity);
        productService.save(product);

        System.out.print("Адрес доставки: ");
        String address = scanner.nextLine();

        int newOrderId = orderService.findAll().size() + 1;
        Order order = new Order(newOrderId, currentUserId, address, "NEW");
        orderService.save(order);

        int newOrderItemId = orderItemService.findAll().size() + 1;
        OrderItem orderItem = new OrderItem(
                newOrderItemId,
                newOrderId,
                productId,
                quantity,
                product.getPricePerUnit()
        );
        orderItemService.save(orderItem);

        System.out.println("Заказ создан! Номер заказа: " + newOrderId);
    }

    private void viewMyOrders() {
        System.out.println("\n=== Мои заказы ===");

        var orders = orderService.findAll().stream()
                .filter(order -> order.getCustomerId().equals(currentUserId))
                .toList();

        if (orders.isEmpty()) {
            System.out.println("У вас пока нет заказов");
        } else {
            orders.forEach(order -> {
                System.out.println("Заказ #" + order.getOrderId());
                System.out.println("Статус: " + order.getStatus());
                System.out.println("Адрес: " + order.getAddressLine());
                System.out.println("Дата: " + order.getCreatedAt());
                System.out.println("---");
            });
        }
    }

    private void createReview(Scanner scanner) {
        System.out.println("\n=== Создание отзыва ===");

        viewMyOrders();

        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        var orderItems = orderItemService.findAll().stream()
                .filter(item -> item.getOrderId().equals(orderId))
                .toList();

        if (orderItems.isEmpty()) {
            System.out.println("Заказ не найден или пуст");
            return;
        }

        System.out.println("Товары в заказе:");
        orderItems.forEach(item -> {
            var productOpt = productService.findSingle(item.getProductId());
            productOpt.ifPresent(product ->
                    System.out.println("ID товара: " + product.getProductId() +
                            " - " + product.getName()));
        });

        System.out.print("Введите ID товара для отзыва: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Оценка (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        if (rating < 1 || rating > 5) {
            System.out.println("Оценка должна быть от 1 до 5");
            return;
        }

        System.out.print("Комментарий: ");
        String comment = scanner.nextLine();

        var productOpt = productService.findSingle(productId);
        if (productOpt.isEmpty()) {
            System.out.println("Товар не найден");
            return;
        }

        int newReviewId = reviewService.findAll().size() + 1;

        Review review = new Review(newReviewId, currentUserId, productId, rating, comment);
        reviewService.save(review);

        System.out.println("Отзыв успешно добавлен!");

        updateSellerRating(productOpt.get().getSellerId());
    }

    private void updateSellerRating(Integer sellerId) {
        var sellerProducts = productService.findBySellerId(sellerId);

        var allReviews = sellerProducts.stream()
                .flatMap(product -> reviewService.findByProductId(product.getProductId()).stream())
                .toList();

        if (!allReviews.isEmpty()) {
            double averageRating = allReviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);

            var sellerOpt = sellerService.findSingle(sellerId);
            sellerOpt.ifPresent(seller -> {
                seller.setRating((int) Math.round(averageRating));
                sellerService.save(seller);
            });
        }
    }

    private void viewMySales() {
        System.out.println("\n=== Мои продажи ===");

        var sellerOpt = sellerService.findByUserId(currentUserId);
        if (sellerOpt.isEmpty()) {
            System.out.println("Вы не зарегистрированы как продавец");
            return;
        }

        Seller seller = sellerOpt.get();
        var products = productService.findBySellerId(seller.getSellerId());

        System.out.println("Статистика продаж:");
        products.forEach(product -> {
            System.out.println(product.getName() + ": " + product.getQuantity() + " " + product.getUnit() + " в наличии");
        });
    }

    private void viewMyReviews() {
        System.out.println("\n=== Отзывы о моих товарах ===");

        var sellerOpt = sellerService.findByUserId(currentUserId);
        if (sellerOpt.isEmpty()) {
            System.out.println("Вы не зарегистрированы как продавец");
            return;
        }

        Seller seller = sellerOpt.get();
        var sellerProducts = productService.findBySellerId(seller.getSellerId());

        var reviews = sellerProducts.stream()
                .flatMap(product -> reviewService.findByProductId(product.getProductId()).stream())
                .toList();

        if (reviews.isEmpty()) {
            System.out.println("Пока нет отзывов");
        } else {
            reviews.forEach(review -> {
                var productOpt = productService.findSingle(review.getProductId());
                String productName = productOpt.map(Product::getName).orElse("Неизвестный товар");

                System.out.println("Товар: " + productName);
                System.out.println("Оценка: " + review.getRating() + "/5");
                System.out.println("Комментарий: " + review.getComment());
                System.out.println("Дата: " + review.getCreatedAt());
                System.out.println("---");
            });

            double averageRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            System.out.println("Средний рейтинг: " + String.format("%.1f", averageRating) + "/5");
        }
    }

    public static void main(String[] args) {
        Console consoleApp = new Console();
        consoleApp.start();
    }
}
