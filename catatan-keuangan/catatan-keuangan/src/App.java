import adapter.presenter.FinancePresenter;
import adapter.repository.TransactionRepository;
import domain.repository.ITransactionRepository;
import framework.view.FinanceView;
import usecase.FinanceUseCase;

/** Composition Root aplikasi catatan keuangan. */
public class App {
    public static void main(String[] args) {
        ITransactionRepository repository = new TransactionRepository();
        FinanceUseCase useCase = new FinanceUseCase(repository);
        FinancePresenter presenter = new FinancePresenter();
        FinanceView view = new FinanceView(useCase, presenter);
        view.show();
    }
}
