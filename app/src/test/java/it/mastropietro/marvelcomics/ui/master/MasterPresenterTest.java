package it.mastropietro.marvelcomics.ui.master;

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
import it.mastropietro.marvelcomics.usecase.UseCaseFactory;
import rx.Single;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
public class MasterPresenterTest {

    @Mock UseCaseFactory<Integer> useCaseFactory;
    @Mock MasterContract.View viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(useCaseFactory.createUseCase(anyInt())).thenReturn(buildUseCase());
    }

    @SuppressWarnings("unchecked") @Test
    public void whenPresenterStarts_executeUseCaseToRetrieveComics() throws Exception {
        MasterPresenter presenter = new MasterPresenter(useCaseFactory);
        presenter.setViewModel(viewModel);

        presenter.start();

        verify(viewModel).showComicList(ArgumentMatchers.<Comic>anyList());
        verify(viewModel).hideLoading();
    }

    @Test
    public void whenPresenterStops_unsubscribeFromUseCase() throws Exception {
        UseCase mockUseCase = Mockito.mock(UseCase.class);
        MasterPresenter presenter = new MasterPresenter(useCaseFactory);
        presenter.setViewModel(viewModel);
        presenter.getComicsFromCharacterId = mockUseCase;

        presenter.stop();

        verify(mockUseCase).unsubscribe();
        assertNull(presenter.viewModel);
    }

    @Test
    public void whenComicsAreEmpty_shouldntAllowLoadMoreComics() throws Exception {
        MasterPresenter presenter = new MasterPresenter(useCaseFactory);
        presenter.setViewModel(viewModel);

        assertTrue(presenter.canLoadMore);

        presenter.getMoreComics();

        verify(viewModel).showLoading();
        assertFalse(presenter.canLoadMore);
    }

    private UseCase buildUseCase() {
        return new UseCase(Schedulers.io(), Schedulers.immediate()) {
            @Override protected Single buildObservable() {
                List<Comic> comicList = new ArrayList<>();
                return Single.just(comicList);
            }
        };
    }
}