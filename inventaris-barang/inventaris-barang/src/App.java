import adapter.presenter.ItemPresenter;
import adapter.repository.ItemRepository;
import domain.repository.IItemRepository;
import framework.view.ItemView;
import usecase.ItemUseCase;

/**
 * Titik masuk aplikasi inventaris barang (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        IItemRepository itemRepository = new ItemRepository();
        ItemUseCase itemUseCase = new ItemUseCase(itemRepository);
        ItemPresenter itemPresenter = new ItemPresenter();
        ItemView itemView = new ItemView(itemUseCase, itemPresenter);

        itemView.show();
    }
}
