package usecase;

import domain.entity.Item;
import domain.entity.SortOption;
import domain.repository.IItemRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis aplikasi inventaris barang.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil
 * ke layer presenter/view.
 */
public class ItemUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IItemRepository itemRepository;

    public ItemUseCase(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /** Mengambil semua barang yang tersedia. */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /** Menambahkan barang baru dan mengembalikan entity yang tersimpan. */
    public Item addItem(String name, int quantity, String category) {
        return itemRepository.save(name, quantity, category);
    }

    /** Menghapus barang berdasarkan ID. */
    public boolean removeItem(int id) {
        return itemRepository.deleteById(id);
    }

    /**
     * Mengubah data barang secara parsial.
     * Parameter {@code null} berarti field tersebut tidak diubah.
     *
     * @return true jika barang ditemukan dan diperbarui
     */
    public boolean updateItem(int id, String name, Integer quantity, String category) {
        Optional<Item> found = itemRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Item item = found.get();

        // Hanya ubah field yang eksplisit diberikan (bukan null)
        if (name != null) {
            item.changeName(name);
        }
        if (quantity != null) {
            item.changeQuantity(quantity);
        }
        if (category != null) {
            item.changeCategory(category);
        }

        itemRepository.update(item);
        return true;
    }

    /** Mencari barang yang namanya mengandung kata kunci (case-insensitive). */
    public List<Item> searchItems(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return itemRepository.findAll().stream()
                .filter(item -> item.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan barang sesuai kriteria {@link SortOption} yang dipilih. */
    public List<Item> sortItems(SortOption option) {
        return itemRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
