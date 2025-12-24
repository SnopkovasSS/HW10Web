package org.skypro.skyshop.search;

import java.util.List;

public class SearchEngine {
    private final SearchService searchService;
    private final StorageService storageService;

    public SearchEngine(StorageService storage) {
        this.storageService = storage;  // Теперь из search — совместимо!
        this.searchService = new SearchService(storage);  // SearchService использует storage из search
    }

    public List<Searchable> search(String term) {
        return searchService.searchByTerm(term);
    }

    public Searchable findById(int id) {
        return searchService.findById(id);
    }

    public List<Searchable> getAll() {
        return storageService.getAll();
    }

    public void add(Searchable searchable) {
        storageService.add(searchable);
    }
}