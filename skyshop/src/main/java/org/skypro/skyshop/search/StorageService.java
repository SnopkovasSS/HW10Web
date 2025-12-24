package org.skypro.skyshop.search;
import org.skypro.skyshop.model.SimpleProduct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageService {
    private final List<Searchable> storage = new ArrayList<>();
    private final Map<Integer, Searchable> byId = new HashMap<>();

    public StorageService() {
        add(new SimpleProduct(1, "apple phone", 1000.0));
        add(new SimpleProduct(2, "samsung tv", 500.0));
        add(new SimpleProduct(4, "laptop dell", 1500.0));
    }

    public void add(Searchable item) {
        if (item != null) {
            storage.add(item);
            if (item.getId() != 0) {  // Теперь работает!
                byId.put(item.getId(), item);
            }
        }
    }

    public List<Searchable> getAll() {
        return new ArrayList<>(storage);
    }

    public Searchable getById(int id) {
        return byId.get(id);
    }

    public void clear() {
        storage.clear();
        byId.clear();
    }

    public int size() {
        return storage.size();
    }
}
