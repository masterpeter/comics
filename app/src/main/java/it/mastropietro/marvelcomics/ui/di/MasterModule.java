package it.mastropietro.marvelcomics.ui.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.ui.MasterContract;
import it.mastropietro.marvelcomics.usecase.GetComicsFromCharacter;
import it.mastropietro.marvelcomics.usecase.UseCase;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Module
public class MasterModule {

    // TODO: 12/03/17 This could be leaked if the module doesn't die!
    private MasterContract.View viewModel;

    public MasterModule(MasterContract.View viewModel) {
        this.viewModel = viewModel;
    }

    @Provides @Named("getComics")
    UseCase provideGetComicsUseCase(GetComicsFromCharacter useCase) {
        return useCase;
    }

    @Provides MasterContract.View provideViewModel() {
        return viewModel;
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
