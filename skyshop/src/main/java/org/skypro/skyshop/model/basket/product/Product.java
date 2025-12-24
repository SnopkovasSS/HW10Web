package org.skypro.skyshop.model.basket.product;

import org.skypro.skyshop.search.Searchable;

import java.util.UUID;

public interface Product extends Searchable {
    // Дополнительные методы для продуктов (если нужны: getPrice() и т.д.)
    double getPrice();


public interface Product extends Searchable {
    // Дополнительные методы для продуктов (если нужны: getPrice() и т.д.)
    double getPrice();



public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;
    private final double price;

    public Product(UUID id, String name, double price) {
        this.id = (id == null) ? UUID.randomUUID() : id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String getSearchTerm() {
        return getName();  // Поиск по имени продукта
    }

    // Абстрактный метод для форматированной цены
    public abstract String getFormattedPrice();

    // Новый метод: является ли продукт "специальным" (скидка или фиксированная цена)
    public boolean isSpecial() {
        return false;  // По умолчанию для обычных продуктов
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}