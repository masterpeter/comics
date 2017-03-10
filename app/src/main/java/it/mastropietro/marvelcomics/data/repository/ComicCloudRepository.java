package it.mastropietro.marvelcomics.data.repository;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import it.mastropietro.marvelcomics.ComicRepository;
import it.mastropietro.marvelcomics.data.entity.ComicEntity;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Func1;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

class ComicCloudRepository implements ComicRepository {

    private final int characterId;
    private final ComicService comicService;

    @Inject
    public ComicCloudRepository(int characterId,
                                ComicService comicService) {
        this.characterId = characterId;
        this.comicService = comicService;
    }

    @Override public Single<List<Comic>> getComics() {
        return Single.create(comicEntityList())
                .flatMapObservable(convertToSingleItems())
                .map(comicEntityToComic())
                .toList()
                .toSingle();
    }

    @NonNull private Single.OnSubscribe<List<ComicEntity>> comicEntityList() {
        return new Single.OnSubscribe<List<ComicEntity>>() {
            @Override
            public void call(SingleSubscriber<? super List<ComicEntity>> singleSubscriber) {
                try {
                    List<ComicEntity> comicEntitiesFromNetwork = getComicEntitiesFromNetwork();
                    singleSubscriber.onSuccess(comicEntitiesFromNetwork);
                } catch (IOException e) {
                    singleSubscriber.onError(e);
                }
            }
        };
    }

    private List<ComicEntity> getComicEntitiesFromNetwork() throws IOException {
        return comicService.getComicList(characterId)
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
                return new Comic.Builder().build();
            }
        };
    }
}