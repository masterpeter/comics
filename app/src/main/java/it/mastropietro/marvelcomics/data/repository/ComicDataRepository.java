package it.mastropietro.marvelcomics.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import it.mastropietro.marvelcomics.model.Comic;
import it.mastropietro.marvelcomics.usecase.ComicRepository;
import rx.Single;
import rx.functions.Func1;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */
@Singleton @Named("dataRepo")
public class ComicDataRepository implements ComicRepository {

    private final ComicRepository cloudRepo;
    private ComicRepository diskRepo;

    @Inject
    public ComicDataRepository(@Named("cloudRepo") ComicRepository cloudRepo,
                               @Named("diskRepo") ComicRepository diskRepo) {
        this.cloudRepo = cloudRepo;
        this.diskRepo = diskRepo;
    }

    @Override public Single<List<Comic>> getComics(int characterId, int offset) {
        Single<List<Comic>> cloudComics = cloudRepo.getComics(characterId, offset);
        Single<List<Comic>> diskComics = diskRepo.getComics(characterId, offset);
        return diskComics.concatWith(cloudComics).takeFirst(nonEmptyList()).toSingle();
    }

    private Func1<? super List<Comic>, Boolean> nonEmptyList() {
        return new Func1<List<Comic>, Boolean>() {
            @Override public Boolean call(List<Comic> comics) {
                return !comics.isEmpty();
            }
        };
    }

    @Override public void storeComics(List<Comic> comics, int offset) {
        diskRepo.storeComics(comics, offset);
    }
}
