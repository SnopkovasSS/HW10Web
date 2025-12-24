package org.skypro.skyshop.model;

public class DiscountProduct extends SimpleProduct {
    private final double discount;  // Процент скидки (напр. 10.0 для 10%)

    public DiscountProduct(int id, String searchTerm, double price, double discount) {
        super(id, searchTerm, price);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    // Переопределение цены со скидкой (бонус для полноты)
    @Override
    public double getPrice() {
        return super.getPrice() * (1 - discount / 100.0);
    }
}
