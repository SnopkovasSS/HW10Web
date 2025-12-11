package org.skypro.skyshop.model.basket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductBasket {
    private final Map<UUID, Integer> basket;  // ID продукта → количество

    public ProductBasket() {
        this.basket = new HashMap<>();
    }

    // Добавление продукта: увеличивает количество на 1
    public void addProduct(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID продукта не может быть null");
        }
        basket.merge(id, 1, Integer::sum);  // Кратко: merge + sum для инкремента
    }

    // Получение корзины: unmodifiable копия для защиты
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(new HashMap<>(basket));
    }

    @Override
    public String toString() {
        return "ProductBasket{" + "basket=" + basket + '}';
    }
}
