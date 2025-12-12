package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.basket.product.Product;

public final class BasketItem {  // final для неизменяемости
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Количество должно быть >= 1");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }
}

