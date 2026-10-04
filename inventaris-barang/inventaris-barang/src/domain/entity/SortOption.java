package domain.entity;

import java.util.Comparator;

/**
 * Kriteria pengurutan barang.
 * Setiap opsi membawa comparator-nya sendiri sehingga logika pengurutan
 * terpusat di domain, bukan tersebar sebagai angka ajaib di view/use case.
 */
public enum SortOption {
    /** Urutkan nama dari A ke Z (case-insensitive). */
    NAME_ASC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER)),

    /** Urutkan nama dari Z ke A (case-insensitive). */
    NAME_DESC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER).reversed()),

    /** Jumlah stok terkecil terlebih dahulu. */
    QUANTITY_ASC(Comparator.comparingInt(Item::getQuantity)),

    /** Jumlah stok terbesar terlebih dahulu. */
    QUANTITY_DESC(Comparator.comparingInt(Item::getQuantity).reversed());

    /** Comparator yang digunakan untuk mengurutkan daftar barang. */
    private final Comparator<Item> comparator;

    SortOption(Comparator<Item> comparator) {
        this.comparator = comparator;
    }

    /** Mengembalikan comparator yang sesuai dengan opsi ini. */
    public Comparator<Item> comparator() {
        return comparator;
    }
}
