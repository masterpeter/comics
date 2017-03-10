package it.mastropietro.marvelcomics.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import it.mastropietro.marvelcomics.ComicRepository;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ComicDataRepositoryTest {

    private ComicRepository comicRepository;

    @Mock ComicCloudRepository cloudRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(cloudRepository.getComics()).thenReturn(Observable.<List<Comic>>empty());
        comicRepository = new ComicDataRepository(cloudRepository);
    }

    @Test
    public void whenGetComicsIsCalled_returnAnObservableOfComics() throws Exception {
        Observable<List<Comic>> comics = comicRepository.getComics();

        verify(cloudRepository).getComics();
        assertNotNull(comics);
    }
}