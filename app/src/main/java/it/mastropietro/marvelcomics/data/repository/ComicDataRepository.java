package it.mastropietro.marvelcomics.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import it.mastropietro.marvelcomics.ComicRepository;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Observable;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ComicDataRepository implements ComicRepository {

    ComicRepository cloudRepo;

    @Inject
    public ComicDataRepository(@Named("cloudRepo") ComicRepository cloudRepo) {
        this.cloudRepo = cloudRepo;
    }

    @Override public Observable<List<Comic>> getComics() {
        return cloudRepo.getComics();
    }
}
