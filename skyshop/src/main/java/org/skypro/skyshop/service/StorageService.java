package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final List<Product> products;
    private final List<Article> articles;

    public StorageService() {
        this.products = new ArrayList<>();
        this.articles = new ArrayList<>();
        fillTestData();
    }

    private void fillTestData() {
        // Продукты
        products.add(new SimpleProduct(null, "Молоко", 50.0));
        products.add(new SimpleProduct(null, "Хлеб", 30.0));
        products.add(new DiscountedProduct(null, "Молоко со скидкой", 60.0, 20));  // Скидка 20%
        products.add(new FixPriceProduct(null, "Сыр", 150.0));
        products.add(new FixPriceProduct(null, "Смартфон", 25000.0));

        // Статьи
        articles.add(new Article(null, "Java для начинающих", "Введение в Spring Boot."));
        articles.add(new Article(null, "Поиск в Java", "Stream API и фильтрация."));
        articles.add(new Article(null, "Онлайн-магазин", "Структура SkyShop."));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    // Новый метод: все Searchable через Stream (продукты + статьи)
    public List<Searchable> getAllSearchable() {
        return Stream.concat(
                getProducts().stream(),
                getArticles().stream()
        ).collect(Collectors.toList());
    }
}
