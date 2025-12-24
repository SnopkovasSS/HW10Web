package org.skypro.skyshop.model;

import org.skypro.skyshop.search.Searchable;

public class SimpleProduct implements Product, Searchable {
    private final int id;
    private final String name;
    private final double price;

    public SimpleProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getSearchTerm() {
        return name;  // Для поиска
    }
}