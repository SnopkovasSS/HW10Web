package org.skypro.skyshop.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    public SimpleProduct(UUID id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getFormattedPrice() {
        return String.format("%.2f ₽", getPrice());
    }

    @Override
    public boolean isSpecial() {
        return false;  // Обычный продукт
    }

    @Override
    public String toString() {
        return "SimpleProduct{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", formattedPrice='" + getFormattedPrice() + '\'' +
                '}';
    }
}