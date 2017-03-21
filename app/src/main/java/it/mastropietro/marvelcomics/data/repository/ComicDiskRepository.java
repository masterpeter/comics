package it.mastropietro.marvelcomics.data.repository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.data.repository.cache.CacheApi;
import it.mastropietro.marvelcomics.data.repository.cache.CacheFactory;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import rx.Single;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
@Singleton
public class ComicDiskRepository implements ComicRepository {

    private final CacheApi<List<Comic>> cache;

    @Inject @SuppressWarnings("unchecked")
    public ComicDiskRepository(CacheFactory cacheFactory) {
        cache = cacheFactory.createSharedPrefCache();
    }

    @Override public Single<List<Comic>> getComics(int byCharacterId, int offset) {
        if (cache.isCacheExpired()) {
            cache.flush();
            return Single.just(Collections.<Comic>emptyList());
        }
        List<Comic> comicList = cache.get(String.valueOf(offset));
        return Single.just(comicList);
    }

    @Override public void storeComics(List<Comic> comics, int offset) {
        cache.store(String.valueOf(offset), comics);
    }
}
