package it.mastropietro.marvelcomics.data.repository.cache;

import okhttp3.Cache;

/**
 * Created by Angelo Mastropietro on 13/03/17.
 */

public interface CacheFactory {
    Cache createCache();

    CacheApi createSharedPrefCache();
}
