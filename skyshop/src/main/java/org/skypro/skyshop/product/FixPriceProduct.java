package org.skypro.skyshop.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    public FixPriceProduct(UUID id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getFormattedPrice() {
        return String.format("%.2f ₽ (фиксированная)", getPrice());
    }

    @Override
    public boolean isSpecial() {
        return true;  // Фиксированная цена — специальный
    }

    @Override
    public String toString() {
        return "FixPriceProduct{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", formattedPrice='" + getFormattedPrice() + '\'' +
                '}';
    }
}
