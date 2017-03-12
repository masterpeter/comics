package it.mastropietro.marvelcomics.usecase;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import it.mastropietro.marvelcomics.model.Comic;
import rx.Single;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
public class GetComicsFromCharacterTest {

    private static final int CHARACTER_ID = 12345;
    private static final TestScheduler backgroundThread = new TestScheduler();
    private static final TestScheduler mainThread = new TestScheduler();

    @Mock ComicRepository comicRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(comicRepository.getComics(anyInt())).thenAnswer(getComicListAnswer());
    }

    @NonNull private Answer<Single<ArrayList<Comic>>> getComicListAnswer() {
        return new Answer<Single<ArrayList<Comic>>>() {
            @Override
            public Single<ArrayList<Comic>> answer(InvocationOnMock invocation) throws Throwable {
                return Single.just(new ArrayList<Comic>());
            }
        };
    }

    @Test
    public void whenGetComicsUseCaseIsExecuted_comicRepositoryIsCalledToRetrieveObsevable() throws Exception {
        UseCase getComics = new GetComicsFromCharacter(CHARACTER_ID, comicRepository, backgroundThread, mainThread);
        TestSubscriber subscriber = new TestSubscriber();

        getComics.execute(subscriber);

        verify(comicRepository).getComics(anyInt());
    }
}