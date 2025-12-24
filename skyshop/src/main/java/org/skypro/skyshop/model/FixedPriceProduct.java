package org.skypro.skyshop.model;

public class FixedPriceProduct extends SimpleProduct {
    public FixedPriceProduct(int id, String searchTerm, double price) {
        super(id, searchTerm, price);
    }

    // Дополнительная логика: цена неизменна (можно добавить валидацию, если нужно)
    @Override
    public double getPrice() {
        return super.getPrice();  // Фиксированная цена
    }
}
