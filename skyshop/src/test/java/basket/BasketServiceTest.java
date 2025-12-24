package basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.basket.BasketService;
import org.skypro.skyshop.exception.ProductNotFoundException;
import org.skypro.skyshop.model.Product;
import org.skypro.skyshop.model.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.StorageService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasketServiceTest {

    private BasketService basketService;
    private StorageService realStorage;
    private SearchEngine engine;

    @BeforeEach
    void setUp() {
        realStorage = new StorageService();
        engine = new SearchEngine(realStorage);
        basketService = new BasketService(engine);
        // Добавляем продукт в storage
        SimpleProduct product = new SimpleProduct(1, "test product", 100.0);
        realStorage.add(product);
    }

    @Test
    void testAddToBasket() {
        basketService.addToBasket(1, "testStore");
        List<Product> basket = basketService.getBasket("testStore");
        assertEquals(1, basket.size());
        assertEquals("test product", basket.get(0).getName());
    }

    @Test
    void testAddNonExistentProduct() {
        assertThrows(ProductNotFoundException.class, () -> basketService.addToBasket(999, "testStore"));
    }

    @Test
    void testGetEmptyBasket() {
        List<Product> basket = basketService.getBasket("emptyStore");
        assertTrue(basket.isEmpty());
    }

    @Test
    void testClearBasket() {
        basketService.addToBasket(1, "clearStore");
        basketService.clearBasket("clearStore");
        List<Product> basket = basketService.getBasket("clearStore");
        assertTrue(basket.isEmpty());
    }
}