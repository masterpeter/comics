package it.mastropietro.marvelcomics.data.repository.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.data.repository.clock.Clock;
import okhttp3.Cache;

/**
 * Created by Angelo Mastropietro on 13/03/17.
 */
@Singleton
public class CacheFactoryImp implements CacheFactory {

    private static final int FIVE_MB_CACHE = 5 * 1024 * 1024;
    private static final String SHARED_PREF_KEY = "MarvelApp";

    private Context context;
    private final Gson gson;
    private final Clock clock;

    @Inject
    public CacheFactoryImp(Context context,
                           Gson gson,
                           Clock clock) {
        this.context = context;
        this.gson = gson;
        this.clock = clock;
    }

    @Override
    public Cache createCache() {
        File file = retrieveCacheFile();
        return new Cache(file, FIVE_MB_CACHE);
    }

    @Override public CacheApi createSharedPrefCache() {
        return new CacheApiImp(gson, retrieveSharedPref(), clock);
    }

    private SharedPreferences retrieveSharedPref() {
        return context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
    }

    private File retrieveCacheFile() {
        return new File(context.getFilesDir().toURI());
    }
}
