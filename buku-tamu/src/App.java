import adapter.presenter.GuestPresenter;
import adapter.repository.GuestRepository;
import domain.repository.IGuestRepository;
import framework.view.GuestView;
import usecase.GuestUseCase;

/**
 * Titik masuk aplikasi (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        // Layer adapter: implementasi konkret repository (penyimpanan in-memory)
        IGuestRepository guestRepository = new GuestRepository();

        // Layer usecase: logika bisnis, hanya bergantung pada interface repository
        GuestUseCase guestUseCase = new GuestUseCase(guestRepository);

        // Layer adapter: presenter untuk memformat output ke layar
        GuestPresenter guestPresenter = new GuestPresenter();

        // Layer framework: UI konsol yang menerima input user
        GuestView guestView = new GuestView(guestUseCase, guestPresenter);

        // Menjalankan loop menu utama aplikasi
        guestView.show();
    }
}
