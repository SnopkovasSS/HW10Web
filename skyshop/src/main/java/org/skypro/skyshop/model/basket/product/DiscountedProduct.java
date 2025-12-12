package org.skypro.skyshop.model.basket.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int discountPercent;
    private final boolean special = true;  // ← Добавь поле для JSON сериализации (если нужно)

    public DiscountedProduct(UUID id, String name, double price, int discountPercent) {
        super(id, name, price);
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String getFormattedPrice() {
        double discountedPrice = getPrice() * (1 - discountPercent / 100.0);
        return String.format("%.2f ₽ (скидка %d%%)", discountedPrice, discountPercent);
    }

    @Override
    public boolean isSpecial() {
        return true;  // ← Обязательно переопредели: скидочный = special
    }

    public boolean getSpecial() {  // ← Getter для JSON, если Jackson использует его для поля "special"
        return isSpecial();
    }

    @Override
    public String toString() {
        return "DiscountedProduct{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", originalPrice=" + getPrice() +
                ", discountPercent=" + discountPercent + "%" +
                ", formattedPrice='" + getFormattedPrice() + '\'' +
                ", special=" + isSpecial() +  // ← Добавь для toString
                '}';
    }
}