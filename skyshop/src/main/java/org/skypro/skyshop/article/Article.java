package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;
import java.util.UUID;

public class Article implements Searchable {  // implements Searchable (обязательно!)
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

    // Реализация интерфейса Searchable (с @Override)
    @Override
    public String getSearchTerm() {
        return getTitle();  // Поиск по заголовку
    }

    // toString() без @Override (не нужно, просто переопределяем Object)
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
