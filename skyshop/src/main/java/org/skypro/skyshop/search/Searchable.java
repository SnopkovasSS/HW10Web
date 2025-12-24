package org.skypro.skyshop.search;



public interface Searchable {
    String getSearchTerm();  // Для поиска по имени/заголовку

    // Добавь это (int или long — по дизайну твоих продуктов)
    int getId();  // Уникальный ID для идентификации
}
