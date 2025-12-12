package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.basket.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;  // Компонент корзины
    private final StorageService storageService;  // Для проверки продуктов

    // Constructor injection
    public BasketService(StorageService storageService) {
        this.storageService = storageService;
        this.productBasket = new ProductBasket();  // Создаём компонент здесь
    }

    // Метод добавления (из предыдущего)
    public void addToBasket(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        Optional<Product> optionalProduct = storageService.getProductById(id);
        if (!optionalProduct.isPresent()) {
            throw new IllegalArgumentException("Продукт с ID " + id + " не найден");
        }
        productBasket.addProduct(id);  // Добавляем в корзину
    }

    // Новый метод: отображение корзины (map → List<BasketItem> через Stream → UserBasket)
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getProducts();
        if (basketMap.isEmpty()) {
            return new UserBasket(List.of());  // Пустая корзина
        }
        List<BasketItem> items = basketMap.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Optional<Product> optionalProduct = storageService.getProductById(productId);
                    return optionalProduct.map(product -> new BasketItem(product, quantity))
                            .orElse(null);  // Если продукт не найден — null
                })
                .filter(Objects::nonNull)  // Фильтруем null (удалённые продукты)
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}

