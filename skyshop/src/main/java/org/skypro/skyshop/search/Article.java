package org.skypro.skyshop.search;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.id = (id == null) ? UUID.randomUUID() : id;
        this.title = title;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getSearchTerm() {
        return getTitle();  // Поиск по заголовку статьи
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}