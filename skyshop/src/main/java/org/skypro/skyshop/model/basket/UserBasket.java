package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {  // final для неизменяемости
    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("Список items не может быть null");
        }
        this.items = List.copyOf(items);  // Неизменяемая копия списка (Java 10+)
        // Total через Stream: sum(price * quantity для каждого item)
        this.total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "items=" + items.size() + " шт." +
                ", total=" + String.format("%.2f ₽", total) +
                '}';
    }
}
