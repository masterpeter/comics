package it.mastropietro.marvelcomics;

import java.util.List;

import it.mastropietro.marvelcomics.model.Comic;
import rx.Observable;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public interface ComicRepository {

    Observable<List<Comic>> getComics();
}
