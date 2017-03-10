package it.mastropietro.marvelcomics.data.entity.mapper;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public interface Mapper<T, V> {
    V map(T typeToConvert);
}
