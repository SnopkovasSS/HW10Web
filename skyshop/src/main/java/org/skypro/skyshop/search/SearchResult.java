package org.skypro.skyshop.search;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;

public class SearchResult {
    private final String type;  // "Product" или "Article"
    private final Searchable searchable;

    private SearchResult(String type, Searchable searchable) {
        this.type = type;
        this.searchable = searchable;
    }

    // Фабричный метод: создаёт SearchResult на основе типа
    public static SearchResult fromSearchable(Searchable s) {
        if (s instanceof Product) {
            return new SearchResult("Product", s);
        } else if (s instanceof Article) {
            return new SearchResult("Article", s);
        }
        throw new IllegalArgumentException("Unknown searchable type: " + s.getClass());
    }

    public String getType() {
        return type;
    }

    public Searchable getSearchable() {
        return searchable;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "type='"+ type + '\'' +     ", searchable=" + searchable + '}';
    }
}
