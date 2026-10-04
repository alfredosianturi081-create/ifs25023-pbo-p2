package adapter.presenter;

import domain.entity.Item;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Memisahkan logika tampilan dari entity, use case, dan view sehingga
 * format output bisa diubah tanpa menyentuh domain.
 */
public class ItemPresenter {

    /** Memformat satu barang menjadi baris teks untuk ditampilkan. */
    private String format(Item item) {
        return String.format("%d | %s | %d | %s",
                item.getId(), item.getName(), item.getQuantity(), item.getCategory());
    }

    /**
     * Helper umum untuk menampilkan daftar barang.
     * Menampilkan pesan kosong jika list tidak berisi data.
     */
    private void printList(List<Item> items, String header, String emptyMessage) {
        System.out.println(header);

        if (items.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Item item : items) {
            System.out.println(format(item));
        }
    }

    /** Menampilkan daftar semua barang. */
    public void showItems(List<Item> items) {
        printList(items, "Daftar Barang:", "- Data barang belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Item> items, String keyword) {
        printList(items, "Hasil Pencarian: \"" + keyword + "\"", "- Barang tidak ditemukan!");
    }

    /** Menampilkan daftar barang yang sudah diurutkan. */
    public void showSortedItems(List<Item> items) {
        printList(items, "Daftar Barang (Terurut):", "- Data barang belum tersedia!");
    }

    /** Menampilkan pesan sukses setelah menambah barang. */
    public void showAddSuccess(Item item) {
        System.out.printf("Berhasil menambah barang: %s%n", format(item));
    }

    /** Menampilkan pesan sukses setelah menghapus barang. */
    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus barang.");
    }

    /** Menampilkan pesan gagal saat menghapus barang. */
    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus barang dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan sukses setelah mengubah stok barang. */
    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah stok barang.");
    }

    /** Menampilkan pesan gagal saat mengubah barang. */
    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah stok barang dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan saat pilihan menu tidak dikenali. */
    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    /** Menampilkan pesan saat ID yang dimasukkan tidak valid. */
    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    /** Menampilkan pesan saat jumlah stok tidak valid (bukan angka atau kurang dari/sama dengan 0). */
    public void showInvalidQuantity() {
        System.out.println("[!] Jumlah stok tidak valid!");
    }

    /** Menampilkan pesan saat opsi pengurutan tidak valid. */
    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }
}
