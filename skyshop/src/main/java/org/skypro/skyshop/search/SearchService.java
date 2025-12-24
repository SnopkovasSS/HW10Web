package org.skypro.skyshop.search;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final StorageService storage;

    public SearchService(StorageService storage) {
        this.storage = storage;
    }

    public List<Searchable> searchByTerm(String term) {
        return storage.getAll().stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Searchable findById(int id) {
        return storage.getById(id);
    }
}

