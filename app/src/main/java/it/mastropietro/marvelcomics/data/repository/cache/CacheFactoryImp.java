package it.mastropietro.marvelcomics.data.repository.cache;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;

/**
 * Created by Angelo Mastropietro on 13/03/17.
 */
@Singleton
public class CacheFactoryImp implements CacheFactory {

    private static final int FIVE_MB_CACHE = 5 * 1024 * 1024;

    private Context context;

    @Inject
    public CacheFactoryImp(Context context) {
        this.context = context;
    }

    @Override
    public Cache createCache() {
        File file = retrieveCacheFile();
        return new Cache(file, FIVE_MB_CACHE);
    }

    private File retrieveCacheFile() {
        return new File(context.getFilesDir().toURI());
    }
}
