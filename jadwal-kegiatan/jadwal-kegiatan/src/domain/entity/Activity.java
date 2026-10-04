package domain.entity;

import java.util.List;

/**
 * Entity inti yang merepresentasikan satu kegiatan pada jadwal.
 * Berada di layer domain — tidak bergantung pada layer lain dan bebas dari
 * urusan tampilan maupun penyimpanan.
 */
public class Activity {
    /** Urutan hari dalam seminggu (aturan domain), dimulai dari Senin. */
    private static final List<String> DAY_ORDER =
            List.of("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");

    /** ID unik kegiatan, tidak boleh diubah setelah dibuat. */
    private final int id;

    /** Judul kegiatan. */
    private String title;

    /** Hari pelaksanaan kegiatan (mis. Senin). */
    private String day;

    /** Waktu pelaksanaan kegiatan (mis. 08:00). */
    private String time;

    public Activity(int id, String title, String day, String time) {
        this.id = id;
        this.title = title;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    /** Mengubah judul kegiatan. */
    public void changeTitle(String title) {
        this.title = title;
    }

    /** Mengubah hari kegiatan. */
    public void changeDay(String day) {
        this.day = day;
    }

    /** Mengubah waktu kegiatan. */
    public void changeTime(String time) {
        this.time = time;
    }

    /**
     * Posisi hari dalam seminggu (Senin = 0 ... Minggu = 6).
     * Hari yang tidak dikenali ditempatkan paling akhir.
     */
    public int dayOrder() {
        int index = DAY_ORDER.indexOf(day.trim().toLowerCase());
        return index >= 0 ? index : DAY_ORDER.size();
    }
}
