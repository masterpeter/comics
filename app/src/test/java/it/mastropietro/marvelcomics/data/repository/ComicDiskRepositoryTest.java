package it.mastropietro.marvelcomics.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import it.mastropietro.marvelcomics.data.repository.cache.CacheApi;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactory;
import it.mastropietro.marvelcomics.model.Comic;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
public class ComicDiskRepositoryTest {

    @Mock CacheFactory cacheFactory;
    @Mock CacheApi<List<Comic>> cacheApi;

    private static final int FAKE_ID = 12345;
    private static final int OFFSET = 1;

    private ComicDiskRepository diskRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(cacheFactory.createSharedPrefCache()).thenReturn(cacheApi);
        diskRepository = new ComicDiskRepository(cacheFactory);
    }

    @Test
    public void whenCacheIsExpired_flushCacheAndReturnEmptySingle() throws Exception {
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        when(cacheApi.isCacheExpired()).thenReturn(true);

        diskRepository.getComics(FAKE_ID, OFFSET).subscribe(testSubscriber);

        verify(cacheApi).flush();
        testSubscriber.assertNoErrors();
        assertTrue(testSubscriber.getOnNextEvents().get(0).isEmpty());
    }

    @Test
    public void whenCacheIsValid_returnSomeComics() throws Exception {
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        when(cacheApi.isCacheExpired()).thenReturn(false);
        when(cacheApi.get(anyString())).thenReturn(getSimpleComicList());

        diskRepository.getComics(FAKE_ID, OFFSET).subscribe(testSubscriber);

        verify(cacheApi, never()).flush();
        testSubscriber.assertNoErrors();
        assertThat(testSubscriber.getOnNextEvents().get(0), is(not(empty())));
    }

    @Test
    public void whenStoreComicsIsCalled_delegateStoringToCacheApi() throws Exception {
        List<Comic> simpleComicList = getSimpleComicList();
        diskRepository.storeComics(simpleComicList, 1);

        verify(cacheApi).store(eq(String.valueOf(1)), eq(simpleComicList));
    }

    private List<Comic> getSimpleComicList() {
        return Collections.singletonList(new Comic.Builder().id(12).build());
    }

}