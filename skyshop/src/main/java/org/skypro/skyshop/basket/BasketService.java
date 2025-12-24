package org.skypro.skyshop.basket;

import org.skypro.skyshop.exception.ProductNotFoundException;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketService {
    private final SearchEngine searchEngine;
    private final Map<String, List<Product>> baskets = new HashMap<>();

    public BasketService(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public void addToBasket(int id, String storeName) {
        Searchable found = searchEngine.findById(id);
        if (found == null || !(found instanceof Product)) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        Product product = (Product) found;
        baskets.computeIfAbsent(storeName, k -> new ArrayList<>()).add(product);
    }

        public List<Product> getBasket(String storeName) {
        return new ArrayList<>(baskets.getOrDefault(storeName, new ArrayList<>()));
    }

    public void clearBasket(String storeName) {
        baskets.remove(storeName);
    }

    public Map<String, List<Product>> getAllBaskets() {
        return new HashMap<>(baskets);
    }
}