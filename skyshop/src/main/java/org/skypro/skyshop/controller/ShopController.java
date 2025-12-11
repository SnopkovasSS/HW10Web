package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;  // Inject для корзины

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public ResponseEntity<List> getProducts() {
        return ResponseEntity.ok(storageService.getProducts());
    }

    @GetMapping("/articles")
    public ResponseEntity<List> getArticles() {
        return ResponseEntity.ok(storageService.getArticles());
    }

    @GetMapping("/search")
    public ResponseEntity<List> search(@RequestParam String pattern) {
        return ResponseEntity.ok(searchService.search(pattern));
    }

    // Шаг 3: Добавление в корзину (GET /basket/{id}, PathVariable)
    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addToBasket(id);
        return "Продукт успешно добавлен";  // Строка по заданию
    }

    // Шаг 3: Отображение корзины (GET /basket)
    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();  // JSON: items + total
    }
}