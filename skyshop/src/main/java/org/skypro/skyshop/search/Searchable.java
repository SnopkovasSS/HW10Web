package org.skypro.skyshop.search;

public interface Searchable {
    String getSearchTerm();  // Для поиска: name (Product) или title (Article)

    Object getId();
}