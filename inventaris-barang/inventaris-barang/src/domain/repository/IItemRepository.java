package domain.repository;

import domain.entity.Item;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data barang.
 * Interface ini berada di domain agar use case tidak bergantung pada
 * implementasi konkret maupun struktur data yang dipakai untuk menyimpan.
 */
public interface IItemRepository {
    /** Mengambil semua data barang dari penyimpanan. */
    List<Item> findAll();

    /** Mencari satu barang berdasarkan ID. Mengembalikan empty jika tidak ditemukan. */
    Optional<Item> findById(int id);

    /**
     * Menyimpan barang baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @param name nama barang
     * @param quantity jumlah stok
     * @param category kategori barang
     * @return barang yang tersimpan (lengkap dengan ID)
     */
    Item save(String name, int quantity, String category);

    /** Menghapus barang berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);

    /**
     * Menyimpan perubahan pada barang yang sudah ada.
     * Diperlukan agar kontrak tetap benar untuk implementasi yang butuh
     * operasi persist eksplisit (misalnya database).
     */
    void update(Item item);
}
