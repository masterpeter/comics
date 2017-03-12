package it.mastropietro.marvelcomics.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import rx.Single;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
@Singleton @Named("dataRepo")
public class ComicDataRepository implements ComicRepository {

    private final ComicRepository cloudRepo;

    @Inject
    public ComicDataRepository(@Named("cloudRepo") ComicRepository cloudRepo) {
        this.cloudRepo = cloudRepo;
    }

    @Override public Single<List<Comic>> getComics(int characterId, int offset) {
        return cloudRepo.getComics(characterId, offset);
    }
}
