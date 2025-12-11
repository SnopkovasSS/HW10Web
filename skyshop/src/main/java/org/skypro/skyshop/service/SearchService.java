package org.skypro.skyshop.service;


import org.skypro.skyshop.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    // Метод по заданию: поиск через Stream API, возвращает List<SearchResult>
    public List<SearchResult> search(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return List.of();  // Пустой список
        }
        String lowerPattern = pattern.trim().toLowerCase();
        return storageService.getAllSearchable()
                .stream()
                .filter(s -> s.getSearchTerm().toLowerCase().contains(lowerPattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
