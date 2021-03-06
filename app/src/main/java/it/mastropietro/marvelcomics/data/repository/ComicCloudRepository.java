package it.mastropietro.marvelcomics.data.repository;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.data.entity.ComicEntity;
import it.mastropietro.marvelcomics.data.entity.mapper.ComicMapper;
import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Func1;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
@Singleton @Named("cloudRepo")
public class ComicCloudRepository implements ComicRepository {

    private final ComicService comicService;
    private final ComicMapper comicMapper;
    private final ApiKeyProvider apiKeyProvider;

    @Inject
    public ComicCloudRepository(ComicService comicService,
                                ComicMapper comicMapper,
                                ApiKeyProvider apiKeyProvider) {
        this.comicService = comicService;
        this.comicMapper = comicMapper;
        this.apiKeyProvider = apiKeyProvider;
    }

    @Override public Single<List<Comic>> getComics(int characterId, int offset) {
        return Single.create(comicEntityList(characterId, offset))
                .flatMapObservable(convertToSingleItems())
                .map(comicEntityToComic())
                .toList()
                .toSingle();
    }

    @Override public void storeComics(List<Comic> comics, int offset) {
        throw new UnsupportedOperationException("Store comics in the cloud is not supported");
    }

    @NonNull
    private Single.OnSubscribe<List<ComicEntity>> comicEntityList(final int characterId, final int offset) {
        return new Single.OnSubscribe<List<ComicEntity>>() {
            @Override
            public void call(SingleSubscriber<? super List<ComicEntity>> singleSubscriber) {
                try {
                    List<ComicEntity> comicEntitiesFromNetwork = getComicEntitiesFromNetwork(characterId, offset);
                    singleSubscriber.onSuccess(comicEntitiesFromNetwork);
                } catch (IOException e) {
                    singleSubscriber.onError(e);
                }
            }
        };
    }

    private List<ComicEntity> getComicEntitiesFromNetwork(int characterId, int offset) throws IOException {
        return comicService.getComicList(characterId, offset, apiKeyProvider.getQueryMap())
                .execute()
                .body()
                .getData()
                .getComicEntities();
    }

    @NonNull private Func1<List<ComicEntity>, Observable<ComicEntity>> convertToSingleItems() {
        return new Func1<List<ComicEntity>, Observable<ComicEntity>>() {
            @Override public Observable<ComicEntity> call(List<ComicEntity> comicEntities) {
                return Observable.from(comicEntities);
            }
        };
    }

    @NonNull private Func1<ComicEntity, Comic> comicEntityToComic() {
        return new Func1<ComicEntity, Comic>() {
            @Override public Comic call(ComicEntity comicEntity) {
                return comicMapper.map(comicEntity);
            }
        };
    }
}