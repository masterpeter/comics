package it.mastropietro.marvelcomics.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import it.mastropietro.marvelcomics.ComicRepository;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Single;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicDataRepository implements ComicRepository {

    private final ComicRepository cloudRepo;

    @Inject
    public ComicDataRepository(@Named("cloudRepo") ComicRepository cloudRepo) {
        this.cloudRepo = cloudRepo;
    }

    @Override public Single<List<Comic>> getComics() {
        return cloudRepo.getComics();
    }
}
