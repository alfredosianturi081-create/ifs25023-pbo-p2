package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Memisahkan logika tampilan dari entity, use case, dan view sehingga
 * format output bisa diubah tanpa menyentuh domain.
 */
public class GuestPresenter {

    /** Memformat satu tamu menjadi baris teks untuk ditampilkan. */
    private String format(Guest guest) {
        return String.format("%d | %s | %s", guest.getId(), guest.getName(), guest.getPurpose());
    }

    /**
     * Helper umum untuk menampilkan daftar tamu.
     * Menampilkan pesan kosong jika list tidak berisi data.
     */
    private void printList(List<Guest> guests, String header, String emptyMessage) {
        System.out.println(header);

        if (guests.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Guest guest : guests) {
            System.out.println(format(guest));
        }
    }

    /** Menampilkan daftar semua tamu. */
    public void showGuests(List<Guest> guests) {
        printList(guests, "Daftar Tamu:", "- Data tamu belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Guest> guests, String keyword) {
        printList(guests, "Hasil Pencarian: \"" + keyword + "\"", "- Tamu tidak ditemukan!");
    }

    /** Menampilkan pesan sukses setelah mendaftarkan tamu. */
    public void showRegisterSuccess(Guest guest) {
        System.out.printf("Berhasil mendaftarkan tamu: %s%n", format(guest));
    }

    /** Menampilkan pesan sukses setelah menghapus tamu. */
    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus tamu.");
    }

    /** Menampilkan pesan gagal saat menghapus tamu. */
    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus tamu dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan saat pilihan menu tidak dikenali. */
    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    /** Menampilkan pesan saat ID yang dimasukkan tidak valid. */
    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }
}
