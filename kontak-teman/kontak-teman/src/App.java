import adapter.presenter.ContactPresenter;
import adapter.repository.ContactRepository;
import domain.repository.IContactRepository;
import framework.view.ContactView;
import usecase.ContactUseCase;

/**
 * Titik masuk aplikasi Kontak Teman (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        IContactRepository contactRepository = new ContactRepository();
        ContactUseCase contactUseCase = new ContactUseCase(contactRepository);
        ContactPresenter contactPresenter = new ContactPresenter();
        ContactView contactView = new ContactView(contactUseCase, contactPresenter);

        contactView.show();
    }
}
