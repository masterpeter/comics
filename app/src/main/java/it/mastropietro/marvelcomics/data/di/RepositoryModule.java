package it.mastropietro.marvelcomics.data.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.data.repository.Clock;
import it.mastropietro.marvelcomics.data.repository.ClockImpl;
import it.mastropietro.marvelcomics.data.repository.ComicCloudRepository;
import it.mastropietro.marvelcomics.data.repository.ComicDataRepository;
import it.mastropietro.marvelcomics.data.repository.ComicService;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Module
public class RepositoryModule {

    private static final String baseUrl = "http://gateway.marvel.com/";
    private static final String publicKey = "6a7ed890b4b941a925202a5630d5b162";
    private static final String privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

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
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
