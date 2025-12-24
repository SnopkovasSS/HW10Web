package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;  // Импорт интерфейса

public class Article implements Searchable {
    private final int id;
    private final String searchTerm;

        public Article(int id, String searchTerm) {
        this.id = id;
        this.searchTerm = searchTerm;
    }

        @Override
    public int getId() {
        return id;
    }

        @Override
    public String getSearchTerm() {
        return searchTerm;
    }
}