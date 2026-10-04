package domain.entity;

/**
 * Jenis transaksi keuangan.
 * Urutan deklarasi dipakai sebagai urutan alami: INCOME lebih dulu daripada EXPENSE.
 */
public enum TransactionType {
    /** Uang masuk (pemasukan). */
    INCOME,

    /** Uang keluar (pengeluaran). */
    EXPENSE
}
