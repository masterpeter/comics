package it.mastropietro.marvelcomics.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import it.mastropietro.marvelcomics.data.di.NetworkModule;
import it.mastropietro.marvelcomics.data.di.RepositoryModule;
import it.mastropietro.marvelcomics.data.di.UtilsModule;
import it.mastropietro.marvelcomics.ui.master.MasterActivity;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Singleton
@Component(modules = {MasterModule.class, RepositoryModule.class,
        UtilsModule.class, NetworkModule.class})
public interface MasterComponent {
    void inject(MasterActivity activity);
}
