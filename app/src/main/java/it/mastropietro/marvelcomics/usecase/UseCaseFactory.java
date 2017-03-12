package it.mastropietro.marvelcomics.usecase;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */

public interface UseCaseFactory<T> {

    UseCase createUseCase(T... params);
}
