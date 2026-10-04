import adapter.presenter.ActivityPresenter;
import adapter.repository.ActivityRepository;
import domain.repository.IActivityRepository;
import framework.view.ActivityView;
import usecase.ActivityUseCase;

/**
 * Titik masuk aplikasi jadwal kegiatan (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        IActivityRepository activityRepository = new ActivityRepository();
        ActivityUseCase activityUseCase = new ActivityUseCase(activityRepository);
        ActivityPresenter activityPresenter = new ActivityPresenter();
        ActivityView activityView = new ActivityView(activityUseCase, activityPresenter);

        activityView.show();
    }
}
