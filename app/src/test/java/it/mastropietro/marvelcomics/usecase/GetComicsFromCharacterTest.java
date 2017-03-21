package it.mastropietro.marvelcomics.usecase;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;
import rx.Scheduler;
import rx.Single;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
public class GetComicsFromCharacterTest {

    private static final int CHARACTER_ID = 12345;
    private static final Scheduler backgroundThread = Schedulers.io();
    private static final Scheduler mainThread = Schedulers.immediate();

    @Mock ComicRepository comicRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(comicRepository.getComics(anyInt(), anyInt())).thenAnswer(getAnswer());
    }

    @Test
    public void whenGetComicsUseCaseIsExecuted_comicRepositoryIsCalledToRetrieveObservable() throws Exception {
        UseCase getComics = new GetComicsFromCharacter(CHARACTER_ID, comicRepository, backgroundThread, mainThread);

        TestSubscriber subscriber = new TestSubscriber();
        getComics.execute(subscriber);

        subscriber.awaitTerminalEvent();

        verify(comicRepository).getComics(anyInt(), anyInt());
        verify(comicRepository).storeComics(ArgumentMatchers.<Comic>anyList(), anyInt());
    }

    @NonNull private Answer<Single<List<Comic>>> getAnswer() {
        return new Answer<Single<List<Comic>>>() {
            @Override
            public Single<List<Comic>> answer(InvocationOnMock invocation) throws Throwable {
                return Single.just(Collections.singletonList(new Comic.Builder().build()));
            }
        };
    }
}