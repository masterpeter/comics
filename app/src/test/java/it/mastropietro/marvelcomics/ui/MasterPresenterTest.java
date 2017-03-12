package it.mastropietro.marvelcomics.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.UseCase;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
public class MasterPresenterTest {

    @Mock MasterContract.View viewModel;

    private UseCase getComics;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getComics = new FakeUseCase(Schedulers.io(), Schedulers.immediate());
    }

    @SuppressWarnings("unchecked") @Test
    public void whenPresenterStarts_executeUseCaseToRetrieveComics() throws Exception {
        MasterPresenter presenter = new MasterPresenter(getComics, viewModel);

        presenter.start();

        verify(viewModel).showComicList(ArgumentMatchers.<Comic>anyList());
        verify(viewModel).hideLoading();
    }

    @Test
    public void whenPresenterStops_unsubscribeFromUseCase() throws Exception {
        UseCase mockUseCase = Mockito.mock(UseCase.class);
        MasterPresenter presenter = new MasterPresenter(mockUseCase, viewModel);

        presenter.stop();

        verify(mockUseCase).unsubscribe();
    }

    private static class FakeUseCase extends UseCase {

        FakeUseCase(Scheduler backgroundThread, Scheduler mainThread) {
            super(backgroundThread, mainThread);
        }

        @Override protected Single buildObservable() {
            List<Comic> comicList = new ArrayList<>();
            comicList.add(new Comic.Builder().build());
            return Single.just(comicList);
        }
    }
}