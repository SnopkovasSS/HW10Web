package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> availableProducts;  // ← По заданию: Map для .get(id)
    private final List<Article> articles;

    public StorageService() {
        this.availableProducts = new HashMap<>();
        this.articles = new ArrayList<>();
        fillTestData();
    }

    private void fillTestData() {
        // Продукты: заполняем availableProducts Map
        Product milk = new SimpleProduct(null, "Молоко", 50.0);
        availableProducts.put(milk.getId(), milk);

        Product bread = new SimpleProduct(null, "Хлеб", 30.0);
        availableProducts.put(bread.getId(), bread);

        Product discountedMilk = new DiscountedProduct(null, "Молоко со скидкой", 60.0, 20);
        availableProducts.put(discountedMilk.getId(), discountedMilk);

        Product cheese = new FixPriceProduct(null, "Сыр", 150.0);
        availableProducts.put(cheese.getId(), cheese);

        Product smartphone = new FixPriceProduct(null, "Смартфон", 25000.0);
        availableProducts.put(smartphone.getId(), smartphone);

        // Статьи (как раньше)
        articles.add(new Article(null, "Java для начинающих", "Введение в Spring Boot."));
        articles.add(new Article(null, "Поиск в Java", "Stream API и фильтрация."));
        articles.add(new Article(null, "Онлайн-магазин", "Структура SkyShop."));
    }

    // getProducts(): возвращаем List из Map (для совместимости)
    public List<Product> getProducts() {
        return new ArrayList<>(availableProducts.values());
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    // По заданию: метод для поиска по ID
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    // getAllSearchable() (оставь как есть, используй availableProducts.values().stream())
    public List<Searchable> getAllSearchable() {
        Stream<Product> productStream = availableProducts.values().stream();
        Stream<Article> articleStream = getArticles().stream();
        return Stream.concat(productStream, articleStream)
                .collect(Collectors.toList());
    }
}
