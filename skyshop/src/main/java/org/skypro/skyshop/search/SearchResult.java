package org.skypro.skyshop.search;

import java.util.UUID;

public class SearchResult {
    private String searchTerm;
    private UUID id;  // Или другие поля по необходимости

    // Конструктор
    public SearchResult(String searchTerm, UUID id) {
        this.searchTerm = searchTerm;
        this.id = id;
    }

    // Static factory: fromSearchable (теперь обрабатывает все типы — без switch, просто копирует)
    public static SearchResult fromSearchable(Searchable s) {
        if (s == null) return null;
        // Универсально для всех Searchable (без проверки типа — фиксит "Unknown type")
        return new SearchResult(s.getSearchTerm(), UUID.randomUUID());  // Адаптируй id если есть getId() в Searchable
    }

    // Геттеры
    public String getSearchTerm() { return searchTerm; }
    public UUID getId() { return id; }
}