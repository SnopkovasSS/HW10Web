package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// SearchEngine — сервис для поиска по Searchable объектам (Product/Article).
// Ищет по getSearchTerm() (case-insensitive, по подстроке).
// Можно расширить: полное слово, по ID и т.д.
public class SearchEngine {
    private List<Searchable> items;  // Коллекция для индекса (Product + Article)

    // Конструктор: инициализирует пустой индекс.
    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    // Добавляет объект в индекс (для поиска).
    public void addItem(Searchable item) {
        if (item != null) {
            items.add(item);
        }
    }

    // Добавляет коллекцию.
    public void addItems(List<Searchable> newItems) {
        if (newItems != null) {
            items.addAll(newItems);
        }
    }

    // Поиск по запросу: возвращает совпадения.
    // @param query Строка поиска (не null).
    // @return List<Searchable> (пустой, если ничего не найдено).
    // @throws IllegalArgumentException если query null/empty.
    public List<Searchable> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Запрос поиска не может быть null или пустым");
        }
        String lowerQuery = query.toLowerCase().trim();
        return items.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(lowerQuery))  // Case-insensitive подстрока
                .collect(Collectors.toList());
    }

    // Поиск по ID (уникальный).
    public Searchable findById(UUID id) {
        if (id == null) {
            return null;
        }
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Получить все: для теста.
    public List<Searchable> getAll() {
        return new ArrayList<>(items);  // Копия для immutable
    }

    // Очистить индекс.
    public void clear() {
        items.clear();
    }

    // toString: количество результатов.
    @Override
    public String toString() {
        return "SearchEngine: " + items.size() + " объектов в индексе";
    }
}