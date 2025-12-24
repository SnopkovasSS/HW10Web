package org.skypro.skyshop.model.basket.product;

public class SimpleProduct implements Product {
    private final int id;
    private final String searchTerm;  // Имя для поиска
    private final double price;

    public SimpleProduct(int id, String searchTerm, double price) {
        this.id = id;
        this.searchTerm = searchTerm;
        this.price = price;
    }

    @Override
    public String getSearchTerm() {
        return searchTerm;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    // Геттеры для удобства (если нужны)
    public int getSimpleId() { return id; }  // Дубликат для примера
}