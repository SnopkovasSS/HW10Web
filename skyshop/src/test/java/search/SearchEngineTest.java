package search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.StorageService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchEngineTest {

    @Mock private StorageService mockStorage;
    private SearchEngine searchEngine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchEngine = new SearchEngine(mockStorage);
    }

    // Вспомогательный метод для создания мока Searchable
    private Searchable createMockSearchable(String term, int id) {
        Searchable mock = mock(Searchable.class);
        when(mock.getSearchTerm()).thenReturn(term);
        when(mock.getId()).thenReturn(id);
        return mock;
    }

    // Сценарий 1: Отсутствие объектов в StorageService
    @Test
    void
    search_WhenStorageEmpty_ShouldReturnEmptyList() {
        when(mockStorage.getAll()).thenReturn(Collections.emptyList());

        List<Searchable> result = searchEngine.search("test");

        assertTrue(result.isEmpty());
        verify(mockStorage).getAll();
    }

    // Сценарий 2: Объекты есть, но нет подходящих
    @Test
    void search_WhenItemsExistButNoMatch_ShouldReturnEmptyList() {
        Searchable item1 = createMockSearchable("Apple", 1);
        Searchable item2 = createMockSearchable("Banana", 2);
        List<Searchable> items = Arrays.asList(item1, item2);
        when(mockStorage.getAll()).thenReturn(items);

        List<Searchable> result = searchEngine.search("Cherry");

        assertTrue(result.isEmpty());
        verify(mockStorage).getAll();
    }

    // Сценарий 3: Есть подходящий объект
    @Test
    void search_WhenMatchingItemExists_ShouldReturnMatches() {
        Searchable match = createMockSearchable("Red Apple", 1);
        Searchable noMatch = createMockSearchable("Banana", 2);
        List<Searchable> items = Arrays.asList(match, noMatch);
        when(mockStorage.getAll()).thenReturn(items);

        List<Searchable> result = searchEngine.search("apple");

        assertEquals(1, result.size());
        assertEquals("Red Apple", result.get(0).getSearchTerm());
        verify(mockStorage).getAll();
    }

    // Доп. сценарий: Несколько совпадений
    @Test
    void search_WhenMultipleMatches_ShouldReturnAll() {
        Searchable match1 = createMockSearchable("Apple Pie", 1);
        Searchable match2 = createMockSearchable("Green Apple", 2);
        Searchable noMatch = createMockSearchable("Banana", 3);
        List<Searchable> items = Arrays.asList(match1, match2, noMatch);
        when(mockStorage.getAll()).thenReturn(items);

        List<Searchable> result = searchEngine.search("apple");

        assertEquals(2, result.size());
        verify(mockStorage).getAll();
    }

    // Доп. сценарий: Пустой запрос
    @Test
    void search_WhenQueryEmpty_ShouldReturnEmptyList() {
        List<Searchable> result = searchEngine.search("");

        assertTrue(result.isEmpty());
        verify(mockStorage, never()).getAll();  // Не вызываем Storage
    }
}

