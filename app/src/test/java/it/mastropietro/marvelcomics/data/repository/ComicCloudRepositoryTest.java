package it.mastropietro.marvelcomics.data.repository;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import it.mastropietro.marvelcomics.usecase.ComicRepository;
import it.mastropietro.marvelcomics.data.TestUtils;
import it.mastropietro.marvelcomics.data.entity.mapper.ComicMapper;
import it.mastropietro.marvelcomics.model.Comic;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Single;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
public class ComicCloudRepositoryTest {

    private static final int FAKE_CHARACTER_ID = 12345;
    private static MockWebServer mockWebServer;
    private static ComicRepository cloudRepository;

    @BeforeClass
    public static void staticSetUp() throws Exception {
        setupMockWebServer();
        setupComicCloudRepository();
    }

    private static void setupMockWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    private static void setupComicCloudRepository() {
        HttpUrl baseUrl = mockWebServer.url("/");
        ComicService comicService = getRetrofit(baseUrl).create(ComicService.class);
        ApiKeyProvider apiKeyProvider = Mockito.mock(ApiKeyProvider.class);
        when(apiKeyProvider.getQueryMap()).thenReturn(new HashMap<String, String>());
        cloudRepository = new ComicCloudRepository(comicService, new ComicMapper(), apiKeyProvider);
    }

    private static Retrofit getRetrofit(HttpUrl baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Before
    public void setUp() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody(buildComicResponse()));
    }

    @Test
    public void whenGetCountryIsCalledFromCloud_returnAnObservableWithComics() throws Exception {
        Single<List<Comic>> singleComicList = cloudRepository.getComics(FAKE_CHARACTER_ID);

        assertNotNull(singleComicList);
    }

    @Test
    public void whenGetCountryIsCalledFromCloud_returnSingleWithExactlyOneList() throws Exception {
        Single<List<Comic>> singleComicList = cloudRepository.getComics(FAKE_CHARACTER_ID);

        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        singleComicList.subscribe(testSubscriber);
        List<List<Comic>> onNextEvents = testSubscriber.getOnNextEvents();

        assertThat(onNextEvents.size(), is(1));
    }

    @Test
    public void whenGetCountryIsCalledFromCloud_returnExpectedItems() throws Exception {
        Single<List<Comic>> singleComicList = cloudRepository.getComics(FAKE_CHARACTER_ID);

        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        singleComicList.subscribe(testSubscriber);
        List<Comic> comicList = testSubscriber.getOnNextEvents().get(0);

        assertThat(comicList.get(0).getId(), is(62725));
    }

    private static String buildComicResponse() {
        return TestUtils.readFile("comic_response.json");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        mockWebServer.close();
    }
}