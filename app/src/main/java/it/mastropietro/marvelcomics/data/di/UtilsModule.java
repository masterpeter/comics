package it.mastropietro.marvelcomics.data.di;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.mastropietro.marvelcomics.data.repository.clock.Clock;
import it.mastropietro.marvelcomics.data.repository.clock.ClockImpl;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
@Module
public class UtilsModule {

    // These should be stored in Android KeyStore... let's keep them here for now :)
    private static final String publicKey = "6a7ed890b4b941a925202a5630d5b162";
    private static final String privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    private final Context applicationContext;

    public UtilsModule(Context applicationContext) {
        this.applicationContext = applicationContext;
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
    Gson gson() {
        return new Gson();
    }

    @Singleton @Provides
    Context context() {
        return applicationContext;
    }
}
