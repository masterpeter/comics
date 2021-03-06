package it.mastropietro.marvelcomics.ui.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.usecase.ComicsUseCaseFactory;
import it.mastropietro.marvelcomics.usecase.UseCaseFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Module
public class MasterModule {

    @Provides @Named("comicsUseCaseFactory")
    UseCaseFactory<Integer> provideGetComicsUseCase(ComicsUseCaseFactory useCaseFactory) {
        return useCaseFactory;
    }

    @Provides
    int characterId() {
        return 1009220;
    }

    @Provides @Named("backgroundThread") Scheduler provideBackGroundScheduler() {
        return Schedulers.io();
    }

    @Provides @Named("mainThread") Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
