package it.mastropietro.marvelcomics.data.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.data.repository.ComicCloudRepository;
import it.mastropietro.marvelcomics.data.repository.ComicDataRepository;
import it.mastropietro.marvelcomics.data.repository.ComicDiskRepository;
import it.mastropietro.marvelcomics.data.repository.ComicService;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import retrofit2.Retrofit;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Module
public class RepositoryModule {

    @Singleton @Provides @Named("dataRepo")
    ComicRepository comicRepository(ComicDataRepository repository) {
        return repository;
    }

    @Singleton @Provides @Named("cloudRepo")
    ComicRepository comicCloudRepository(ComicCloudRepository repository) {
        return repository;
    }

    @Singleton @Provides @Named("diskRepo")
    ComicRepository comicDiskRepository(ComicDiskRepository repository) {
        return repository;
    }

    @Singleton @Provides
    ComicService provideComicService(Retrofit retrofit) {
        return retrofit.create(ComicService.class);
    }
}
