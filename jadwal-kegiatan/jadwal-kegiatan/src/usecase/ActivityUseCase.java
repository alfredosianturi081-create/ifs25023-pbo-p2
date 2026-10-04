package usecase;

import domain.entity.Activity;
import domain.entity.SortOption;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis aplikasi jadwal kegiatan.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil
 * ke layer presenter/view.
 */
public class ActivityUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IActivityRepository activityRepository;

    public ActivityUseCase(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /** Mengambil semua kegiatan yang tersedia. */
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /** Menambahkan kegiatan baru dan mengembalikan entity yang tersimpan. */
    public Activity addActivity(String title, String day, String time) {
        return activityRepository.save(title, day, time);
    }

    /** Menghapus kegiatan berdasarkan ID. */
    public boolean removeActivity(int id) {
        return activityRepository.deleteById(id);
    }

    /**
     * Mengubah judul, hari, dan/atau waktu kegiatan.
     * Parameter {@code null} berarti field tersebut tidak diubah.
     *
     * @return true jika kegiatan ditemukan dan diperbarui
     */
    public boolean updateActivity(int id, String title, String day, String time) {
        Optional<Activity> found = activityRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Activity activity = found.get();

        // Hanya ubah field yang eksplisit diberikan (bukan null)
        if (title != null) {
            activity.changeTitle(title);
        }
        if (day != null) {
            activity.changeDay(day);
        }
        if (time != null) {
            activity.changeTime(time);
        }

        activityRepository.update(activity);
        return true;
    }

    /** Mencari kegiatan yang judulnya mengandung kata kunci (case-insensitive). */
    public List<Activity> searchActivities(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return activityRepository.findAll().stream()
                .filter(activity -> activity.getTitle().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan kegiatan sesuai kriteria {@link SortOption} yang dipilih. */
    public List<Activity> sortActivities(SortOption option) {
        return activityRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
