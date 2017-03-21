package it.mastropietro.marvelcomics.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Named;

import it.mastropietro.marvelcomics.Constants;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Scheduler;
import rx.Single;
import rx.functions.Action1;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */

public class GetComicsFromCharacter extends UseCase {

    private int pageNumber;
    private final int characterId;
    private final ComicRepository comicRepository;

    public GetComicsFromCharacter(int characterId,
                                  @Named("dataRepo") ComicRepository comicRepository,
                                  @Named("backgroundThread") Scheduler backgroundThread,
                                  @Named("mainThread") Scheduler mainThread) {
        super(backgroundThread, mainThread);
        this.characterId = characterId;
        this.comicRepository = comicRepository;
    }

    @Override protected Single buildObservable() {
        return comicRepository
                .getComics(characterId, getOffset())
                .doOnSuccess(storeComicsInCache());
    }

    @NonNull private Action1<List<Comic>> storeComicsInCache() {
        return new Action1<List<Comic>>() {
            @Override public void call(List<Comic> comics) {
                comicRepository.storeComics(comics, getOffset());
            }
        };
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private int getOffset() {
        return pageNumber * Constants.COMIC_LIMIT;
    }
}
