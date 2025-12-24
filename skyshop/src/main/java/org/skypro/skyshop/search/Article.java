package org.skypro.skyshop.search;

public class Article implements Searchable {
    private final int id;
    private final String searchTerm;

    public Article(int id, String searchTerm) {
        this.id = id;
        this.searchTerm = searchTerm;
    }

    @Override
    public String getSearchTerm() {
        return searchTerm;
    }

    @Override
    public int getId() {
        return id;
    }
}