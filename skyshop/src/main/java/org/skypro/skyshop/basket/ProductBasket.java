package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private final List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}