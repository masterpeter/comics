package it.mastropietro.marvelcomics.data.repository;

import java.util.List;

import it.mastropietro.marvelcomics.ComicRepository;
import it.mastropietro.marvelcomics.model.Comic;
import rx.Observable;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

class ComicCloudRepository implements ComicRepository {

    @Override public Observable<List<Comic>> getComics() {
        return null;
    }
}
