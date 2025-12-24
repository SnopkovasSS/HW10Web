package org.skypro.skyshop.model;

public class PriceProduct extends SimpleProduct {
    public PriceProduct(int id, String searchTerm, double price) {
        super(id, searchTerm, price);
    }

    // Логика для динамической цены (бонус: метод для расчёта с налогом)
    public double getPriceWithTax(double taxRate) {
        return super.getPrice() * (1 + taxRate / 100.0);
    }
}
