package org.skypro.skyshop.controller;

import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public ResponseEntity<List> getProducts() {
        return ResponseEntity.ok(storageService.getProducts());
    }

    @GetMapping("/articles")
    public ResponseEntity<List> getArticles() {
        return ResponseEntity.ok(storageService.getArticles());
    }

    // Шаг 6: /search?pattern=...
    @GetMapping("/search")
    public ResponseEntity<List> search(@RequestParam String pattern) {
        return ResponseEntity.ok(searchService.search(pattern));
    }
}
