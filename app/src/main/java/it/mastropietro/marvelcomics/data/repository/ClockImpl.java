package it.mastropietro.marvelcomics.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
@Singleton
public class ClockImpl implements Clock {

    @Inject
    public ClockImpl() {
    }

    @Override public long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
