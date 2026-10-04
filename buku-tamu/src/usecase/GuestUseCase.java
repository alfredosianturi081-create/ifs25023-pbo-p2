package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.List;

/**
 * Use case yang menangani logika bisnis aplikasi buku tamu.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil
 * ke layer presenter/view.
 */
public class GuestUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IGuestRepository guestRepository;

    public GuestUseCase(IGuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    /** Mengambil semua tamu yang tersedia. */
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    /** Mendaftarkan tamu baru dan mengembalikan entity yang tersimpan. */
    public Guest registerGuest(String name, String purpose) {
        return guestRepository.save(name, purpose);
    }

    /** Menghapus tamu berdasarkan ID. */
    public boolean removeGuest(int id) {
        return guestRepository.deleteById(id);
    }

    /** Mencari tamu yang namanya mengandung kata kunci (case-insensitive). */
    public List<Guest> searchGuests(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return guestRepository.findAll().stream()
                .filter(guest -> guest.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }
}
