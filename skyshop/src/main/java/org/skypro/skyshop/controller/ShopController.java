package org.skypro.skyshop.controller;

import org.skypro.skyshop.basket.BasketService;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class ShopController {
    private final SearchEngine searchEngine;
    private final BasketService basketService;

    public ShopController(SearchEngine searchEngine, BasketService basketService) {
        this.searchEngine = searchEngine;
        this.basketService = basketService;
    }

    // Поиск продуктов (не Spring, просто метод)
    public List<Searchable> searchProducts(String term) {
        return searchEngine.search(term);
    }

    // Добавить в корзину
    public String addToBasket(int id, String storeName) {
        try {
            basketService.addToBasket(id, storeName);
            return "Product added to basket!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Получить содержимое корзины
    public List<Product> getBasket(String storeName) {
        return basketService.getBasket(storeName);
    }

    // Бонус: Получить все продукты
    public List<Searchable> getAllProducts() {
        return searchEngine.getAll();
    }
}