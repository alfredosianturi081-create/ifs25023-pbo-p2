package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Memisahkan logika tampilan dari entity, use case, dan view sehingga
 * format output bisa diubah tanpa menyentuh domain.
 */
public class ContactPresenter {

    /** Memformat satu kontak menjadi baris teks untuk ditampilkan. */
    private String format(Contact contact) {
        return String.format("%d | %s | %s | %s",
                contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
    }

    /**
     * Helper umum untuk menampilkan daftar kontak.
     * Menampilkan pesan kosong jika list tidak berisi data.
     */
    private void printList(List<Contact> contacts, String header, String emptyMessage) {
        System.out.println(header);

        if (contacts.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Contact contact : contacts) {
            System.out.println(format(contact));
        }
    }

    /** Menampilkan daftar semua kontak. */
    public void showContacts(List<Contact> contacts) {
        printList(contacts, "Daftar Kontak:", "- Data kontak belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Contact> contacts, String keyword) {
        printList(contacts, "Hasil Pencarian: \"" + keyword + "\"", "- Kontak tidak ditemukan!");
    }

    /** Menampilkan daftar kontak yang sudah diurutkan. */
    public void showSortedContacts(List<Contact> contacts) {
        printList(contacts, "Daftar Kontak (Terurut):", "- Data kontak belum tersedia!");
    }

    /** Menampilkan pesan sukses setelah menambah kontak. */
    public void showAddSuccess(Contact contact) {
        System.out.printf("Berhasil menambah kontak: %s%n", format(contact));
    }

    /** Menampilkan pesan sukses setelah menghapus kontak. */
    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus kontak.");
    }

    /** Menampilkan pesan gagal saat menghapus kontak. */
    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus kontak dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan sukses setelah mengubah kontak. */
    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah kontak.");
    }

    /** Menampilkan pesan gagal saat mengubah kontak. */
    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah kontak dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan saat pilihan menu tidak dikenali. */
    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    /** Menampilkan pesan saat ID yang dimasukkan tidak valid. */
    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    /** Menampilkan pesan saat opsi pengurutan tidak valid. */
    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }
}
