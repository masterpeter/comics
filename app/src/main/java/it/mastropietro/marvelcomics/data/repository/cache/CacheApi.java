package it.mastropietro.marvelcomics.data.repository.cache;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
public interface CacheApi<T> {

    void store(String key, T t);

    T get(String key);

    boolean isCacheExpired();

    void flush();
}
