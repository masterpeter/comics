package it.mastropietro.marvelcomics.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public class ComicsUseCaseFactory implements UseCaseFactory<Integer> {

    private final int characterId;
    private final ComicRepository comicRepository;
    private final Scheduler backgroundThread;
    private final Scheduler mainThread;

    @Inject
    public ComicsUseCaseFactory(int characterId,
                                @Named("dataRepo") ComicRepository comicRepository,
                                @Named("backgroundThread") Scheduler backgroundThread,
                                @Named("mainThread") Scheduler mainThread) {
        this.characterId = characterId;
        this.comicRepository = comicRepository;
        this.backgroundThread = backgroundThread;
        this.mainThread = mainThread;
    }

    @Override public UseCase createUseCase(Integer... params) {
        GetComicsFromCharacter useCase = new GetComicsFromCharacter(characterId, comicRepository,
                backgroundThread,
                mainThread);
        useCase.setPageNumber(params[0]);
        return useCase;
    }
}
