package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;  // Новый импорт
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.basket.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(StorageService storageService) {
        this.storageService = storageService;
        this.productBasket = new ProductBasket();
    }

    // Метод добавления: проверка + add (теперь NoSuchProductException)
    public void addToBasket(UUID id) {
        if (id == null) {
            throw new NoSuchProductException("ID не может быть null");  // Можно оставить как NoSuch... или IllegalArg, но по заданию — везде заменить
        }
        Optional<Product> optionalProduct = storageService.getProductById(id);
        if (!optionalProduct.isPresent()) {
            throw new NoSuchProductException("Продукт с ID " + id + " не найден");  // Замена по заданию
        }
        productBasket.addProduct(id);
    }

    // Метод отображения: без изменений (фильтр null, не throw)
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getProducts();
        if (basketMap.isEmpty()) {
            return new UserBasket(List.of());
        }
        List<BasketItem> items = basketMap.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Optional<Product> optionalProduct = storageService.getProductById(productId);
                    return optionalProduct.map(product -> new BasketItem(product, quantity))
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}