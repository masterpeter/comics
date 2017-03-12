package it.mastropietro.marvelcomics.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Single;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */

public class GetComicsFromCharacter extends UseCase {

    private final int characterId;
    private final ComicRepository comicRepository;

    @Inject
    public GetComicsFromCharacter(int characterId,
                                  @Named("dataRepo") ComicRepository comicRepository,
                                  @Named("backgroundThread") Scheduler backgroundThread,
                                  @Named("mainThread") Scheduler mainThread) {
        super(backgroundThread, mainThread);
        this.characterId = characterId;
        this.comicRepository = comicRepository;
    }

    @Override protected Single buildObservable() {
        return comicRepository.getComics(characterId);
    }
}
