package it.mastropietro.marvelcomics.data.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactory;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactoryImp;
import it.mastropietro.marvelcomics.data.repository.clock.Clock;
import it.mastropietro.marvelcomics.data.repository.clock.ClockImpl;
import it.mastropietro.marvelcomics.data.repository.ComicCloudRepository;
import it.mastropietro.marvelcomics.data.repository.ComicDataRepository;
import it.mastropietro.marvelcomics.data.repository.ComicService;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Module
public class RepositoryModule {

    private static final String baseUrl = "http://gateway.marvel.com/";

    // These should be stored in Android KeyStore... let's keep them here for now :)
    private static final String publicKey = "6a7ed890b4b941a925202a5630d5b162";
    private static final String privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    private final Context applicationContext;

    public RepositoryModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Singleton @Provides @Named("dataRepo")
    ComicRepository comicRepository(ComicDataRepository repository) {
        return repository;
    }

    @Singleton @Provides @Named("cloudRepo")
    ComicRepository comicCloudRepository(ComicCloudRepository repository) {
        return repository;
    }

    @Singleton @Provides
    Clock provideClock(ClockImpl clock) {
        return clock;
    }

    @Singleton @Provides @Named("publicKey")
    String providePublicKey() {
        return publicKey;
    }

    @Singleton @Provides @Named("privateKey")
    String providePrivateKey() {
        return privateKey;
    }

    @Singleton @Provides
    ComicService provideComicService(Retrofit retrofit) {
        return retrofit.create(ComicService.class);
    }

    @Singleton @Provides
    Context context() {
        return applicationContext;
    }

    @Singleton @Provides
    CacheFactory cacheFactory(CacheFactoryImp cacheFactoryImp) {
        return cacheFactoryImp;
    }

    @Singleton @Provides
    OkHttpClient httpClient(CacheFactory cache) {
        return new OkHttpClient.Builder()
                .cache(cache.createCache())
                .build();
    }

    @Singleton @Provides
    Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
