package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 * Berada di layer adapter — mengimplementasikan port dari domain sekaligus
 * menyembunyikan detail struktur data dari layer di atasnya.
 */
public class ItemRepository implements IItemRepository {
    /** Penyimpanan data barang di memori. */
    private final List<Item> data = new ArrayList<>();

    /** Penghitung ID otomatis, bertambah setiap kali barang baru disimpan. */
    private int idCounter = 0;

    @Override
    public List<Item> findAll() {
        // Salinan defensif agar data internal tidak bisa diubah dari luar
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Item> findById(int id) {
        return data.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    @Override
    public Item save(String name, int quantity, String category) {
        Item item = new Item(nextId(), name, quantity, category);
        data.add(item);
        return item;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(item -> item.getId() == id);
    }

    @Override
    public void update(Item item) {
        // Entity bersifat mutable dan disimpan by-reference, sehingga perubahan
        // pada instance sudah otomatis tercermin di penyimpanan in-memory.
        // Method ini tetap ada agar kontrak port valid untuk implementasi lain.
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
