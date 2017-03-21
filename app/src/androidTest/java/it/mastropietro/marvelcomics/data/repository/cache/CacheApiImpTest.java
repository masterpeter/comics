package it.mastropietro.marvelcomics.data.repository.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import it.mastropietro.marvelcomics.data.repository.clock.Clock;
import it.mastropietro.marvelcomics.model.Comic;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
@RunWith(AndroidJUnit4.class)
public class CacheApiImpTest {

    private static final long FAKE_TIMESTAMP = 1000L;

    @Mock Clock clock;

    private CacheApiImp cacheApi;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(clock.getCurrentTimeInMillis()).thenReturn(FAKE_TIMESTAMP);
        sharedPreferences = getTestSharedPref();
        cacheApi = new CacheApiImp(new Gson(), sharedPreferences, clock);
    }

    @Test
    public void whenCacheApiIsCreated_initializeCacheTimestamp() throws Exception {
        String timestamp = sharedPreferences.getString(CacheApiImp.CACHE_EXPIRE_KEY, "");

        assertEquals(String.valueOf(FAKE_TIMESTAMP), timestamp);

        when(clock.getCurrentTimeInMillis()).thenReturn(12345L);
        cacheApi = new CacheApiImp(new Gson(), sharedPreferences, clock);
        timestamp = sharedPreferences.getString(CacheApiImp.CACHE_EXPIRE_KEY, "");

        assertNotEquals(12345L, timestamp);
    }

    @Test
    public void whenGetIsCalledWithEmptyData_returnEmptyList() throws Exception {
        List<Comic> comicList = cacheApi.get("pluto");

        assertThat(comicList, is(empty()));
    }

    @Test
    public void whenGetIsCalledWithSomeData_returnExpectedData() throws Exception {
        fillCacheWithSomething("pluto");

        List<Comic> comicList = cacheApi.get("pluto");

        assertThat(comicList.size(), is(1));
        assertThat(comicList.get(0).getId(), is(12));
    }

    @Test
    public void whenFlushIsCalled_clearDataAndReInitTimestamp() throws Exception {
        fillCacheWithSomething("pluto");
        when(clock.getCurrentTimeInMillis()).thenReturn(12345L);

        assertThat(cacheApi.get("pluto"), is(not(Matchers.<Comic>empty())));

        cacheApi.flush();

        assertThat(cacheApi.get("pluto"), is(Matchers.<Comic>empty()));
        assertEquals(String.valueOf(12345L), sharedPreferences.getString(CacheApiImp.CACHE_EXPIRE_KEY, ""));
    }

    @SuppressLint("ApplySharedPref") @After
    public void tearDown() throws Exception {
        sharedPreferences.edit().clear().commit();
    }

    private void fillCacheWithSomething(String key) {
        Comic comic = new Comic.Builder().id(12).build();
        List<Comic> comics = Collections.singletonList(comic);
        cacheApi.store(key, comics);
    }

    private SharedPreferences getTestSharedPref() {
        return InstrumentationRegistry.getTargetContext().getSharedPreferences("TestComic", Context.MODE_PRIVATE);
    }
}