package domain.repository;

import domain.entity.Guest;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data tamu.
 * Interface ini berada di domain agar use case tidak bergantung pada
 * implementasi konkret maupun struktur data yang dipakai untuk menyimpan.
 */
public interface IGuestRepository {
    /** Mengambil semua data tamu dari penyimpanan. */
    List<Guest> findAll();

    /** Mencari satu tamu berdasarkan ID. Mengembalikan empty jika tidak ditemukan. */
    Optional<Guest> findById(int id);

    /**
     * Menyimpan tamu baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @param name nama tamu
     * @param purpose tujuan kunjungan
     * @return tamu yang tersimpan (lengkap dengan ID)
     */
    Guest save(String name, String purpose);

    /** Menghapus tamu berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);
}
