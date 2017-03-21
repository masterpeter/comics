package it.mastropietro.marvelcomics.data.repository;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import rx.Single;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ComicDataRepositoryTest {

    private static final int FAKE_CHARACTER_ID = 12345;
    private ComicRepository comicRepository;

    @Mock ComicCloudRepository cloudRepository;
    @Mock ComicDiskRepository diskRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(cloudRepository.getComics(FAKE_CHARACTER_ID, 0)).thenReturn(getFakeComicsObservable());
        when(diskRepository.getComics(FAKE_CHARACTER_ID, 0)).thenReturn(Single.just(Collections.<Comic>emptyList()));
        comicRepository = new ComicDataRepository(cloudRepository, diskRepository);
    }

    @Test
    public void whenGetComicsIsCalled_returnAnObservableOfComics() throws Exception {
        Single<List<Comic>> comics = comicRepository.getComics(FAKE_CHARACTER_ID, 0);
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        comics.subscribe(testSubscriber);

        verify(cloudRepository).getComics(FAKE_CHARACTER_ID, 0);
        verify(diskRepository).getComics(FAKE_CHARACTER_ID, 0);
        assertNotNull(comics);
        testSubscriber.assertNoErrors();

        List<List<Comic>> listsFromComicObservable = testSubscriber.getOnNextEvents();
        assertThat(listsFromComicObservable.size(), is(1));
        assertThat(listsFromComicObservable.get(0).size(), is(3));
    }

    @NonNull private Single<List<Comic>> getFakeComicsObservable() {
        List<Comic> comicList = new ArrayList<>();
        comicList.add(new Comic.Builder().id(1).build());
        comicList.add(new Comic.Builder().id(2).build());
        comicList.add(new Comic.Builder().id(3).build());
        return Single.just(comicList);
    }
}