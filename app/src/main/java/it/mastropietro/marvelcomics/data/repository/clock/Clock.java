package it.mastropietro.marvelcomics.data.repository.clock;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */

public interface Clock {
    long getCurrentTimeInMillis();

    boolean isCurrentTimeAfter(String timestampToCompare);
}
