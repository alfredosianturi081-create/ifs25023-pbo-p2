package adapter.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 * Berada di layer adapter — mengimplementasikan port dari domain sekaligus
 * menyembunyikan detail struktur data dari layer di atasnya.
 */
public class TransactionRepository implements ITransactionRepository {
    /** Penyimpanan data transaksi di memori. */
    private final List<Transaction> data = new ArrayList<>();

    /** Penghitung ID otomatis, bertambah setiap kali transaksi baru disimpan. */
    private int idCounter = 0;

    @Override
    public List<Transaction> findAll() {
        // Salinan defensif agar data internal tidak bisa diubah dari luar
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Transaction> findById(int id) {
        return data.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    @Override
    public Transaction save(String description, long amount, TransactionType type) {
        Transaction transaction = new Transaction(nextId(), description, amount, type);
        data.add(transaction);
        return transaction;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(transaction -> transaction.getId() == id);
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
