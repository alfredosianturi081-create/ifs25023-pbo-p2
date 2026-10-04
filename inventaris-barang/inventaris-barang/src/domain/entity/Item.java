package domain.entity;

/**
 * Entity inti yang merepresentasikan satu barang di inventaris.
 * Berada di layer domain — tidak bergantung pada layer lain dan bebas dari
 * urusan tampilan maupun penyimpanan.
 */
public class Item {
    /** ID unik barang, tidak boleh diubah setelah dibuat. */
    private final int id;

    /** Nama barang. */
    private String name;

    /** Jumlah stok barang. */
    private int quantity;

    /** Kategori barang. */
    private String category;

    public Item(int id, String name, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    /** Mengubah nama barang. */
    public void changeName(String name) {
        this.name = name;
    }

    /** Mengubah jumlah stok barang. */
    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** Mengubah kategori barang. */
    public void changeCategory(String category) {
        this.category = category;
    }
}
