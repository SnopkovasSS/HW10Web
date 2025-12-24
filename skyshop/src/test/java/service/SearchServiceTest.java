package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;

public class SearchServiceTest {
    @Mock
    private StorageService mockStorage;
    private SearchEngine searchEngine; // Реальный, инжектим мок

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchEngine = new SearchEngine(mockStorage); // Или как у тебя конструктор
    }

    // Шаг 1.1: Поиск без объектов в Storage
    @Test
    void search_WhenStorageEmpty_ShouldReturnEmptyList() {
        when(mockStorage.getAll()).thenReturn(Collections.emptyList()); // Твой метод getAll()?

        List<Searchable> result = searchEngine.search("test");

        assertTrue(result.isEmpty());
        verify(mockStorage).getAll();
    }

    // Шаг 1.2: Объекты есть, но нет совпадений
    @Test
    void search_WhenNoMatchingItems_ShouldReturnEmptyList() {
        Searchable item1 = mock(Searchable.class);
        when(item1.getTitle()).thenReturn("Apple"); // Реальный метод?
        Searchable item2 = mock(Searchable.class);
        when(item2.getTitle()).thenReturn("Banana");
        List<Searchable> items = Arrays.asList(item1, item2);
        when(mockStorage.getAll()).thenReturn(items);

        List<Searchable> result = searchEngine.search("Cherry");

        assertTrue(result.isEmpty());
        verify(mockStorage).getAll();
        // Добавь verify, что matches() или contains вызван, если в коде
    }

    // Шаг 1.3: Есть совпадение
    @Test
    void search_WhenMatchingItemExists_ShouldReturnMatches() {
        Searchable match = mock(Searchable.class);
        when(match.getTitle()).thenReturn("Red Apple");
        Searchable noMatch = mock(Searchable.class);
        when(noMatch.getTitle()).thenReturn("Banana");
        List<Searchable> items = Arrays.asList(match, noMatch);
        when(mockStorage.getAll()).thenReturn(items);

        List<Searchable> result = searchEngine.search("Apple");

        assertEquals(1, result.size());
        assertEquals("Red Apple", result.get(0).getTitle());
        verify(mockStorage).getAll();
    }

    // Доп. сценарий: Несколько совпадений
    @Test
    void search_WhenMultipleMatches_ShouldReturnAll() {
        // Аналогично, создай 2 matching + 1 no
        // ... (код как выше, assertEquals(2, result.size()))
    }

    // Для BasketServiceTest.java аналогично (в отдельном файле)
    // Пришли код SearchEngine или Searchable, если нужно точнее!
}


