package adapter.presenter;

import domain.entity.Activity;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Memisahkan logika tampilan dari entity, use case, dan view sehingga
 * format output bisa diubah tanpa menyentuh domain.
 */
public class ActivityPresenter {

    /** Memformat satu kegiatan menjadi baris teks untuk ditampilkan. */
    private String format(Activity activity) {
        return String.format("%d | %s | %s | %s",
                activity.getId(), activity.getTitle(), activity.getDay(), activity.getTime());
    }

    /**
     * Helper umum untuk menampilkan daftar kegiatan.
     * Menampilkan pesan kosong jika list tidak berisi data.
     */
    private void printList(List<Activity> activities, String header, String emptyMessage) {
        System.out.println(header);

        if (activities.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Activity activity : activities) {
            System.out.println(format(activity));
        }
    }

    /** Menampilkan daftar semua kegiatan. */
    public void showActivities(List<Activity> activities) {
        printList(activities, "Daftar Kegiatan:", "- Data kegiatan belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Activity> activities, String keyword) {
        printList(activities, "Hasil Pencarian: \"" + keyword + "\"", "- Kegiatan tidak ditemukan!");
    }

    /** Menampilkan daftar kegiatan yang sudah diurutkan. */
    public void showSortedActivities(List<Activity> activities) {
        printList(activities, "Daftar Kegiatan (Terurut):", "- Data kegiatan belum tersedia!");
    }

    /** Menampilkan pesan sukses setelah menambah kegiatan. */
    public void showAddSuccess(Activity activity) {
        System.out.printf("Berhasil menambah kegiatan: %s%n", format(activity));
    }

    /** Menampilkan pesan sukses setelah menghapus kegiatan. */
    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus kegiatan.");
    }

    /** Menampilkan pesan gagal saat menghapus kegiatan. */
    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus kegiatan dengan ID: %d.%n", id);
    }

    /** Menampilkan pesan sukses setelah mengubah kegiatan. */
    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah kegiatan.");
    }

    /** Menampilkan pesan gagal saat mengubah kegiatan. */
    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah kegiatan dengan ID: %d.%n", id);
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
