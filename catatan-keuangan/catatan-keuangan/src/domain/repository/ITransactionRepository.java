package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data transaksi.
 * Interface ini berada di domain agar use case tidak bergantung pada
 * implementasi konkret maupun struktur data yang dipakai untuk menyimpan.
 */
public interface ITransactionRepository {
    /** Mengambil semua data transaksi dari penyimpanan. */
    List<Transaction> findAll();

    /** Mencari satu transaksi berdasarkan ID. Mengembalikan empty jika tidak ditemukan. */
    Optional<Transaction> findById(int id);

    /**
     * Menyimpan transaksi baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @param description keterangan transaksi
     * @param amount jumlah uang
     * @param type jenis transaksi
     * @return transaksi yang tersimpan (lengkap dengan ID)
     */
    Transaction save(String description, long amount, TransactionType type);

    /** Menghapus transaksi berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);
}
