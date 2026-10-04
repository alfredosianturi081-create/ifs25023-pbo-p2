package domain.entity;

/**
 * Entity inti yang merepresentasikan satu kontak teman.
 * Berada di layer domain — tidak bergantung pada layer lain dan bebas dari
 * urusan tampilan maupun penyimpanan.
 */
public class Contact {
    /** ID unik kontak, tidak boleh diubah setelah dibuat. */
    private final int id;

    /** Nama kontak. */
    private String name;

    /** Nomor telepon kontak. */
    private String phone;

    /** Alamat email kontak. */
    private String email;

    public Contact(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    /** Mengubah nama kontak. */
    public void changeName(String name) {
        this.name = name;
    }

    /** Mengubah nomor telepon kontak. */
    public void changePhone(String phone) {
        this.phone = phone;
    }

    /** Mengubah email kontak. */
    public void changeEmail(String email) {
        this.email = email;
    }
}
