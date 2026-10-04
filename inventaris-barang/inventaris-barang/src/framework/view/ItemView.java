package framework.view;

import adapter.presenter.ItemPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.ItemUseCase;

/**
 * Tampilan konsol aplikasi inventaris barang.
 * Menerima input user, memanggil use case, dan menampilkan hasil via presenter.
 * Layer ini tidak mengandung logika bisnis — hanya menangani interaksi user.
 */
public class ItemView {
    /** Use case yang menjalankan operasi bisnis. */
    private final ItemUseCase itemUseCase;

    /** Presenter yang memformat hasil ke output layar. */
    private final ItemPresenter presenter;

    public ItemView(ItemUseCase itemUseCase, ItemPresenter presenter) {
        this.itemUseCase = itemUseCase;
        this.presenter = presenter;
    }

    /** Menampilkan menu utama dan loop interaksi user. */
    public void show() {
        boolean running = true;

        while (running) {
            // Tampilkan daftar barang terkini sebelum menu
            presenter.showItems(itemUseCase.getAllItems());
            printMenu();
            String input = InputUtil.input("Pilih");

            switch (input) {
                case "1" -> addItem();
                case "2" -> updateStock();
                case "3" -> searchItem();
                case "4" -> sortItem();
                case "5" -> removeItem();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }

            if (running) {
                System.out.println();
            }
        }
    }

    /** Mencetak opsi menu ke layar. */
    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah Stok");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    /** Form tambah barang baru. */
    private void addItem() {
        System.out.println("[Menambah Barang]");
        String name = InputUtil.input("Nama (x Jika Batal)");
        if (name.equals("x")) {
            return;
        }

        Integer quantity = parseQuantity(InputUtil.input("Jumlah"));
        if (quantity == null) {
            return;
        }

        String category = InputUtil.input("Kategori (x Jika Batal)");
        if (category.equals("x")) {
            return;
        }

        presenter.showAddSuccess(itemUseCase.addItem(name, quantity, category));
    }

    /** Form ubah stok barang (kosongkan jika tidak ingin mengubah). */
    private void updateStock() {
        System.out.println("[Mengubah Stok]");
        String strId = InputUtil.input("ID Barang yang diubah (x Jika Batal)");
        if (strId.equals("x")) {
            return;
        }

        Integer id = parseId(strId);
        if (id == null) {
            return;
        }

        String strQuantity = InputUtil.input("Jumlah Baru (Kosongkan jika tidak ingin mengubah)");

        // null berarti stok tidak diubah
        Integer quantity = null;
        if (!strQuantity.isBlank()) {
            quantity = parseQuantity(strQuantity);
            if (quantity == null) {
                return;
            }
        }

        if (itemUseCase.updateItem(id, null, quantity, null)) {
            presenter.showUpdateSuccess();
        } else {
            presenter.showUpdateFailed(id);
        }
    }

    /** Form cari barang berdasarkan nama. */
    private void searchItem() {
        System.out.println("[Mencari Barang]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equals("x")) {
            presenter.showSearchResults(itemUseCase.searchItems(keyword), keyword);
        }
    }

    /** Form urutkan barang berdasarkan pilihan user. */
    private void sortItem() {
        System.out.println("[Mengurutkan Barang]");
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("3. Jumlah (Terkecil -> Terbesar)");
        System.out.println("4. Jumlah (Terbesar -> Terkecil)");
        System.out.println("x. Batal");
        String input = InputUtil.input("Pilih");
        if (input.equals("x")) {
            return;
        }

        // Konversi input angka ke enum domain
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }

        presenter.showSortedItems(itemUseCase.sortItems(option));
    }

    /** Form hapus barang berdasarkan ID. */
    private void removeItem() {
        System.out.println("[Menghapus Barang]");
        String strId = InputUtil.input("[ID Barang] yang dihapus (x Jika Batal)");
        if (strId.equals("x")) {
            return;
        }

        Integer id = parseId(strId);
        if (id == null) {
            return;
        }

        if (itemUseCase.removeItem(id)) {
            presenter.showRemoveSuccess();
        } else {
            presenter.showRemoveFailed(id);
        }
    }

    /**
     * Mengonversi input string menjadi ID.
     *
     * @return ID jika valid, null jika tidak valid (error sudah ditampilkan)
     */
    private Integer parseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            presenter.showInvalidId();
            return null;
        }
    }

    /**
     * Mengonversi input string menjadi jumlah stok (harus numerik dan &gt; 0).
     *
     * @return jumlah stok jika valid, null jika tidak valid (error sudah ditampilkan)
     */
    private Integer parseQuantity(String value) {
        try {
            int quantity = Integer.parseInt(value);
            if (quantity > 0) {
                return quantity;
            }
        } catch (NumberFormatException e) {
            // jatuh ke penanganan error di bawah
        }
        presenter.showInvalidQuantity();
        return null;
    }

    /** Memetakan pilihan menu (1-4) ke {@link SortOption} domain. */
    private SortOption mapSortOption(String input) {
        return switch (input) {
            case "1" -> SortOption.NAME_ASC;
            case "2" -> SortOption.NAME_DESC;
            case "3" -> SortOption.QUANTITY_ASC;
            case "4" -> SortOption.QUANTITY_DESC;
            default -> null;
        };
    }
}
